import { useState, useEffect } from 'react';
import { getAllLaboratories, createLaboratory, toggleLaboratoryActive } from '../../api/laboratoryApi';

export default function AdminLaboratories() {
  const [labs, setLabs] = useState([]);
  const [showForm, setShowForm] = useState(false);
  const [form, setForm] = useState({ name: '', address: '', city: '', state: '', contactNumber: '', active: true });
  const [loading, setLoading] = useState(true);
  const [submitting, setSubmitting] = useState(false);

  const fetchData = async () => {
    try { const r = await getAllLaboratories(); setLabs(r.data); } catch (e) { console.error(e); }
    setLoading(false);
  };
  useEffect(() => { fetchData(); }, []);

  const handleChange = (e) => setForm({ ...form, [e.target.name]: e.target.value });

  const handleSubmit = async (e) => {
    e.preventDefault();
    setSubmitting(true);
    try { await createLaboratory(form); setShowForm(false); setForm({ name: '', address: '', city: '', state: '', contactNumber: '', active: true }); fetchData(); } catch (e) { alert('Failed to create laboratory'); }
    setSubmitting(false);
  };

  const handleToggle = async (id) => {
    try { await toggleLaboratoryActive(id); fetchData(); } catch (e) { alert('Failed'); }
  };

  if (loading) return <div className="flex justify-center py-12"><div className="animate-spin rounded-full h-12 w-12 border-b-2 border-blue-600"></div></div>;

  return (
    <div className="space-y-6">
      <div className="flex justify-between items-center">
        <h1 className="text-2xl font-bold text-gray-900">Manage Laboratories</h1>
        <button onClick={() => setShowForm(!showForm)} className="px-4 py-2 bg-blue-600 text-white rounded-lg hover:bg-blue-700 text-sm font-medium">
          {showForm ? 'Cancel' : '+ Add Laboratory'}
        </button>
      </div>

      {showForm && (
        <form onSubmit={handleSubmit} className="bg-white rounded-xl shadow-md p-6 space-y-4">
          <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
            {[
              { name: 'name', label: 'Name', placeholder: 'Central Lab' },
              { name: 'address', label: 'Address', placeholder: '456 Lab Street' },
              { name: 'city', label: 'City', placeholder: 'Bangalore' },
              { name: 'state', label: 'State', placeholder: 'Karnataka' },
              { name: 'contactNumber', label: 'Phone', placeholder: '+91 9876543210' },
            ].map(f => (
              <div key={f.name}>
                <label className="block text-sm font-medium text-gray-700 mb-1">{f.label}</label>
                <input name={f.name} value={form[f.name]} onChange={handleChange} required placeholder={f.placeholder}
                  className="w-full px-3 py-2 border border-gray-300 rounded-lg" />
              </div>
            ))}
          </div>
          <button type="submit" disabled={submitting} className="px-6 py-2.5 bg-blue-600 text-white rounded-lg hover:bg-blue-700 disabled:opacity-50 font-medium">
            {submitting ? 'Creating...' : 'Create Laboratory'}
          </button>
        </form>
      )}

      <div className="bg-white rounded-xl shadow-md overflow-hidden">
        <table className="min-w-full divide-y divide-gray-200">
          <thead className="bg-gray-50">
            <tr>
              <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase">Name</th>
              <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase">Address</th>
              <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase">Phone</th>
              <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase">Status</th>
              <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase">Action</th>
            </tr>
          </thead>
          <tbody className="divide-y divide-gray-200">
            {labs.map((l) => (
              <tr key={l.id}>
                <td className="px-6 py-4 text-sm text-gray-900">{l.name}</td>
                <td className="px-6 py-4 text-sm text-gray-500">{l.address}</td>
                <td className="px-6 py-4 text-sm text-gray-500">{l.contactNumber}</td>
                <td className="px-6 py-4 text-sm">
                  <span className={`px-2 py-1 rounded-full text-xs font-medium ${l.active ? 'bg-green-100 text-green-800' : 'bg-red-100 text-red-800'}`}>
                    {l.active ? 'Active' : 'Inactive'}
                  </span>
                </td>
                <td className="px-6 py-4 text-sm">
                  <button onClick={() => handleToggle(l.id)}
                    className={`px-3 py-1.5 text-xs rounded-lg ${l.active ? 'bg-red-50 text-red-600 hover:bg-red-100' : 'bg-green-50 text-green-600 hover:bg-green-100'}`}>
                    {l.active ? 'Deactivate' : 'Activate'}
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

