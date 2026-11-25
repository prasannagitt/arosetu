import { useState, useEffect } from 'react';
import { getAllHospitals, createHospital, toggleHospitalActive } from '../../api/hospitalApi';

export default function AdminHospitals() {
  const [hospitals, setHospitals] = useState([]);
  const [showForm, setShowForm] = useState(false);
  const [form, setForm] = useState({ name: '', address: '', city: '', state: '', contactNumber: '', email: '', pinCode: '', type: 'HOSPITAL', active: true });
  const [loading, setLoading] = useState(true);
  const [submitting, setSubmitting] = useState(false);

  const fetchData = async () => {
    try { const r = await getAllHospitals(); setHospitals(r.data); } catch (e) { console.error(e); }
    setLoading(false);
  };
  useEffect(() => { fetchData(); }, []);

  const handleChange = (e) => setForm({ ...form, [e.target.name]: e.target.value });

  const handleSubmit = async (e) => {
    e.preventDefault();
    setSubmitting(true);
    try { await createHospital(form); setShowForm(false); setForm({ name: '', address: '', city: '', state: '', contactNumber: '', email: '', pinCode: '', type: 'HOSPITAL', active: true }); fetchData(); } catch (e) { alert('Failed to create hospital'); }
    setSubmitting(false);
  };

  const handleToggle = async (id) => {
    try { await toggleHospitalActive(id); fetchData(); } catch (e) { alert('Failed'); }
  };

  if (loading) return <div className="flex justify-center py-12"><div className="animate-spin rounded-full h-12 w-12 border-b-2 border-blue-600"></div></div>;

  return (
    <div className="space-y-6">
      <div className="flex justify-between items-center">
        <h1 className="text-2xl font-bold text-gray-900">Manage Hospitals</h1>
        <button onClick={() => setShowForm(!showForm)} className="px-4 py-2 bg-blue-600 text-white rounded-lg hover:bg-blue-700 text-sm font-medium">
          {showForm ? 'Cancel' : '+ Add Hospital'}
        </button>
      </div>

      {showForm && (
        <form onSubmit={handleSubmit} className="bg-white rounded-xl shadow-md p-6 space-y-4">
          <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
            {[
              { name: 'name', label: 'Hospital Name', placeholder: 'City General Hospital' },
              { name: 'address', label: 'Address', placeholder: '123 Health Street' },
              { name: 'city', label: 'City', placeholder: 'Mumbai' },
              { name: 'state', label: 'State', placeholder: 'Maharashtra' },
              { name: 'contactNumber', label: 'Phone', placeholder: '+91 9876543210' },
              { name: 'pinCode', label: 'Pin Code', placeholder: '560001' },
              { name: 'email', label: 'Email', placeholder: 'info@hospital.com' },
            ].map(f => (
              <div key={f.name}>
                <label className="block text-sm font-medium text-gray-700 mb-1">{f.label}</label>
                <input name={f.name} value={form[f.name]} onChange={handleChange} required placeholder={f.placeholder}
                  className="w-full px-3 py-2 border border-gray-300 rounded-lg" />
              </div>
            ))}
            <div>
              <label className="block text-sm font-medium text-gray-700 mb-1">Type</label>
              <select name="type" value={form.type} onChange={handleChange} className="w-full px-3 py-2 border border-gray-300 rounded-lg">
                <option value="HOSPITAL">Hospital</option>
                <option value="CLINIC">Clinic</option>
                <option value="DIAGNOSTIC_CENTER">Diagnostic Center</option>
              </select>
            </div>
          </div>
          <button type="submit" disabled={submitting} className="px-6 py-2.5 bg-blue-600 text-white rounded-lg hover:bg-blue-700 disabled:opacity-50 font-medium">
            {submitting ? 'Creating...' : 'Create Hospital'}
          </button>
        </form>
      )}

      <div className="bg-white rounded-xl shadow-md overflow-hidden">
        <table className="min-w-full divide-y divide-gray-200">
          <thead className="bg-gray-50">
            <tr>
              <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase">Name</th>
              <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase">City</th>
              <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase">Type</th>
              <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase">Status</th>
              <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase">Action</th>
            </tr>
          </thead>
          <tbody className="divide-y divide-gray-200">
            {hospitals.map((h) => (
              <tr key={h.id}>
                <td className="px-6 py-4 text-sm text-gray-900">{h.name}</td>
                <td className="px-6 py-4 text-sm text-gray-500">{h.city}</td>
                <td className="px-6 py-4 text-sm text-gray-500">{h.type}</td>
                <td className="px-6 py-4 text-sm">
                  <span className={`px-2 py-1 rounded-full text-xs font-medium ${h.active ? 'bg-green-100 text-green-800' : 'bg-red-100 text-red-800'}`}>
                    {h.active ? 'Active' : 'Inactive'}
                  </span>
                </td>
                <td className="px-6 py-4 text-sm">
                  <button onClick={() => handleToggle(h.id)}
                    className={`px-3 py-1.5 text-xs rounded-lg ${h.active ? 'bg-red-50 text-red-600 hover:bg-red-100' : 'bg-green-50 text-green-600 hover:bg-green-100'}`}>
                    {h.active ? 'Deactivate' : 'Activate'}
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
}

