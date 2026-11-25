import { useState, useEffect } from 'react';
import { getMyConsultations } from '../../api/consultationApi';
import { createPrescription, getMyPrescriptions, prescribe, sendToPharmacy } from '../../api/prescriptionApi';
import { getActivePharmacies } from '../../api/pharmacyApi';
import StatusBadge from '../../components/StatusBadge';

export default function DoctorPrescriptions() {
  const [prescriptions, setPrescriptions] = useState([]);
  const [consultations, setConsultations] = useState([]);
  const [pharmacies, setPharmacies] = useState([]);
  const [showForm, setShowForm] = useState(false);
  const [form, setForm] = useState({ consultationId: '', notes: '', items: [{ medicineName: '', dosage: '', frequency: '', duration: '', instructions: '' }] });
  const [loading, setLoading] = useState(true);
  const [submitting, setSubmitting] = useState(false);
  const [error, setError] = useState('');

  const fetchData = async () => {
    try {
      const [p, c, ph] = await Promise.all([
        getMyPrescriptions().catch(() => ({ data: [] })),
        getMyConsultations().catch(() => ({ data: [] })),
        getActivePharmacies().catch(() => ({ data: [] })),
      ]);
      setPrescriptions(p.data);
      setConsultations(c.data);
      setPharmacies(ph.data);
    } catch (e) { console.error(e); }
    setLoading(false);
  };
  useEffect(() => { fetchData(); }, []);

  const addItem = () => setForm({ ...form, items: [...form.items, { medicineName: '', dosage: '', frequency: '', duration: '', instructions: '' }] });
  const removeItem = (i) => setForm({ ...form, items: form.items.filter((_, idx) => idx !== i) });
  const updateItem = (i, field, val) => {
    const items = [...form.items];
    items[i] = { ...items[i], [field]: val };
    setForm({ ...form, items });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setSubmitting(true);
    setError('');
    try {
      await createPrescription({ consultationId: parseInt(form.consultationId), notes: form.notes, items: form.items });
      setShowForm(false);
      setForm({ consultationId: '', notes: '', items: [{ medicineName: '', dosage: '', frequency: '', duration: '', instructions: '' }] });
      fetchData();
    } catch (err) {
      setError(err.response?.data?.message || 'Failed to create prescription');
    } finally {
      setSubmitting(false);
    }
  };

  const handlePrescribe = async (id) => {
    try { await prescribe(id); fetchData(); } catch (e) { alert('Failed'); }
  };

  /* handleSendToPharmacy removed — pharmacy selection is handled via the inline <select> dropdown below */

  if (loading) return <div className="flex justify-center py-12"><div className="animate-spin rounded-full h-12 w-12 border-b-2 border-blue-600"></div></div>;

  return (
    <div className="space-y-6">
      <div className="flex justify-between items-center">
        <h1 className="text-2xl font-bold text-gray-900">Prescriptions</h1>
        <button onClick={() => setShowForm(!showForm)}
          className="px-4 py-2 bg-blue-600 text-white rounded-lg hover:bg-blue-700 text-sm font-medium">
          {showForm ? 'Cancel' : '+ New Prescription'}
        </button>
      </div>

      {showForm && (
        <form onSubmit={handleSubmit} className="bg-white rounded-xl shadow-md p-6 space-y-4">
          {error && <div className="bg-red-50 text-red-700 px-4 py-3 rounded-lg text-sm">{error}</div>}
          <div>
            <label className="block text-sm font-medium text-gray-700 mb-1">Consultation</label>
            <select value={form.consultationId} onChange={(e) => setForm({ ...form, consultationId: e.target.value })} required
              className="w-full px-3 py-2 border border-gray-300 rounded-lg">
              <option value="">Select consultation</option>
              {consultations.map(c => (
                <option key={c.id} value={c.id}>#{c.id} — {c.patientName} ({c.consultationDate ? new Date(c.consultationDate).toLocaleDateString() : 'N/A'})</option>
              ))}
            </select>
            {consultations.length === 0 && (
              <p className="text-xs text-amber-600 mt-1">No consultations yet. Create a consultation from the Consultations page first.</p>
            )}
          </div>
          <div>
            <label className="block text-sm font-medium text-gray-700 mb-1">Notes</label>
            <textarea value={form.notes} onChange={(e) => setForm({ ...form, notes: e.target.value })} rows={2}
              className="w-full px-3 py-2 border border-gray-300 rounded-lg" />
          </div>
          <div>
            <div className="flex justify-between items-center mb-2">
              <label className="text-sm font-medium text-gray-700">Medications</label>
              <button type="button" onClick={addItem} className="text-sm text-blue-600 hover:underline">+ Add Medicine</button>
            </div>
            {form.items.map((item, i) => (
              <div key={i} className="p-3 bg-gray-50 rounded-lg mb-2 space-y-2">
                <div className="flex justify-between">
                  <span className="text-xs font-medium text-gray-500">Medicine #{i + 1}</span>
                  {form.items.length > 1 && <button type="button" onClick={() => removeItem(i)} className="text-xs text-red-500">Remove</button>}
                </div>
                <div className="grid grid-cols-2 md:grid-cols-5 gap-2">
                  <input placeholder="Medicine Name" value={item.medicineName} onChange={(e) => updateItem(i, 'medicineName', e.target.value)} required
                    className="px-2 py-1.5 border border-gray-300 rounded text-sm" />
                  <input placeholder="Dosage" value={item.dosage} onChange={(e) => updateItem(i, 'dosage', e.target.value)}
                    className="px-2 py-1.5 border border-gray-300 rounded text-sm" />
                  <input placeholder="Frequency" value={item.frequency} onChange={(e) => updateItem(i, 'frequency', e.target.value)}
                    className="px-2 py-1.5 border border-gray-300 rounded text-sm" />
                  <input placeholder="Duration" value={item.duration} onChange={(e) => updateItem(i, 'duration', e.target.value)}
                    className="px-2 py-1.5 border border-gray-300 rounded text-sm" />
                  <input placeholder="Instructions" value={item.instructions} onChange={(e) => updateItem(i, 'instructions', e.target.value)}
                    className="px-2 py-1.5 border border-gray-300 rounded text-sm" />
                </div>
              </div>
            ))}
          </div>
          <button type="submit" disabled={submitting}
            className="px-6 py-2.5 bg-blue-600 text-white rounded-lg hover:bg-blue-700 disabled:opacity-50 font-medium">
            {submitting ? 'Creating...' : 'Create Prescription'}
          </button>
        </form>
      )}

      {prescriptions.length === 0 ? (
        <div className="bg-white rounded-xl shadow-md p-12 text-center text-gray-500">No prescriptions yet</div>
      ) : (
        <div className="space-y-4">
          {prescriptions.map((p) => (
            <div key={p.id} className="bg-white rounded-xl shadow-md p-6">
              <div className="flex justify-between items-start mb-3">
                <div>
                  <h3 className="font-semibold">Prescription #{p.id} — {p.patientName}</h3>
                  {p.pharmacyName && <p className="text-sm text-gray-500">Pharmacy: {p.pharmacyName}</p>}
                </div>
                <StatusBadge status={p.status} />
              </div>
              {p.items?.length > 0 && (
                <div className="text-sm text-gray-600 mb-3">
                  {p.items.map((item, i) => <span key={i} className="inline-block bg-gray-100 rounded px-2 py-1 mr-1 mb-1">{item.medicineName}</span>)}
                </div>
              )}
              <div className="flex gap-2">
                {p.status === 'DRAFT' && (
                  <button onClick={() => handlePrescribe(p.id)} className="px-3 py-1.5 text-sm bg-green-600 text-white rounded-lg hover:bg-green-700">Prescribe</button>
                )}
                {p.status === 'PRESCRIBED' && (
                  <select onChange={(e) => e.target.value && sendToPharmacy(p.id, e.target.value).then(fetchData)} defaultValue=""
                    className="px-3 py-1.5 text-sm border border-gray-300 rounded-lg">
                    <option value="">Send to Pharmacy...</option>
                    {pharmacies.map(ph => <option key={ph.id} value={ph.id}>{ph.name}</option>)}
                  </select>
                )}
              </div>
            </div>
          ))}
        </div>
      )}
    </div>
  );
}

