import { useState, useEffect } from 'react';
import { getMyAvailability, createAvailability, deleteAvailability } from '../../api/availabilityApi';
import { getActiveHospitals } from '../../api/hospitalApi';

const DAYS = ['MONDAY', 'TUESDAY', 'WEDNESDAY', 'THURSDAY', 'FRIDAY', 'SATURDAY', 'SUNDAY'];

export default function DoctorAvailability() {
  const [availability, setAvailability] = useState([]);
  const [hospitals, setHospitals] = useState([]);
  const [showForm, setShowForm] = useState(false);
  const [form, setForm] = useState({ hospitalId: '', dayOfWeek: 'MONDAY', startTime: '09:00', endTime: '17:00', slotDurationMinutes: 30 });
  const [loading, setLoading] = useState(true);
  const [submitting, setSubmitting] = useState(false);
  const [error, setError] = useState('');

  const fetchData = async () => {
    try {
      const [a, h] = await Promise.all([
        getMyAvailability().catch(() => ({ data: [] })),
        getActiveHospitals().catch(() => ({ data: [] })),
      ]);
      setAvailability(a.data);
      setHospitals(h.data);
    } catch (e) { console.error(e); }
    setLoading(false);
  };
  useEffect(() => { fetchData(); }, []);

  const handleSubmit = async (e) => {
    e.preventDefault();
    setSubmitting(true);
    setError('');
    try {
      await createAvailability({
        hospitalId: parseInt(form.hospitalId),
        dayOfWeek: form.dayOfWeek,
        startTime: form.startTime,
        endTime: form.endTime,
        slotDurationMinutes: parseInt(form.slotDurationMinutes),
      });
      setShowForm(false);
      setForm({ hospitalId: '', dayOfWeek: 'MONDAY', startTime: '09:00', endTime: '17:00', slotDurationMinutes: 30 });
      fetchData();
    } catch (err) {
      setError(err.response?.data?.message || 'Failed to create availability');
    } finally {
      setSubmitting(false);
    }
  };

  const handleDelete = async (id) => {
    if (!confirm('Delete this availability slot?')) return;
    try { await deleteAvailability(id); fetchData(); } catch (e) { alert('Failed to delete'); }
  };

  if (loading) return <div className="flex justify-center py-12"><div className="animate-spin rounded-full h-12 w-12 border-b-2 border-blue-600"></div></div>;

  return (
    <div className="space-y-6">
      <div className="flex justify-between items-center">
        <h1 className="text-2xl font-bold text-gray-900">My Availability</h1>
        <button onClick={() => setShowForm(!showForm)}
          className="px-4 py-2 bg-blue-600 text-white rounded-lg hover:bg-blue-700 text-sm font-medium">
          {showForm ? 'Cancel' : '+ Add Slot'}
        </button>
      </div>

      {showForm && (
        <form onSubmit={handleSubmit} className="bg-white rounded-xl shadow-md p-6 space-y-4">
          {error && <div className="bg-red-50 text-red-700 px-4 py-3 rounded-lg text-sm">{error}</div>}
          <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
            <div>
              <label className="block text-sm font-medium text-gray-700 mb-1">Hospital</label>
              <select value={form.hospitalId} onChange={(e) => setForm({ ...form, hospitalId: e.target.value })} required
                className="w-full px-3 py-2 border border-gray-300 rounded-lg">
                <option value="">Select hospital</option>
                {hospitals.map(h => <option key={h.id} value={h.id}>{h.name}</option>)}
              </select>
            </div>
            <div>
              <label className="block text-sm font-medium text-gray-700 mb-1">Day of Week</label>
              <select value={form.dayOfWeek} onChange={(e) => setForm({ ...form, dayOfWeek: e.target.value })}
                className="w-full px-3 py-2 border border-gray-300 rounded-lg">
                {DAYS.map(d => <option key={d} value={d}>{d.charAt(0) + d.slice(1).toLowerCase()}</option>)}
              </select>
            </div>
            <div>
              <label className="block text-sm font-medium text-gray-700 mb-1">Start Time</label>
              <input type="time" value={form.startTime} onChange={(e) => setForm({ ...form, startTime: e.target.value })} required
                className="w-full px-3 py-2 border border-gray-300 rounded-lg" />
            </div>
            <div>
              <label className="block text-sm font-medium text-gray-700 mb-1">End Time</label>
              <input type="time" value={form.endTime} onChange={(e) => setForm({ ...form, endTime: e.target.value })} required
                className="w-full px-3 py-2 border border-gray-300 rounded-lg" />
            </div>
            <div>
              <label className="block text-sm font-medium text-gray-700 mb-1">Slot Duration (minutes)</label>
              <select value={form.slotDurationMinutes} onChange={(e) => setForm({ ...form, slotDurationMinutes: e.target.value })}
                className="w-full px-3 py-2 border border-gray-300 rounded-lg">
                <option value="15">15 minutes</option>
                <option value="20">20 minutes</option>
                <option value="30">30 minutes</option>
                <option value="45">45 minutes</option>
                <option value="60">60 minutes</option>
              </select>
            </div>
          </div>
          <button type="submit" disabled={submitting}
            className="px-6 py-2.5 bg-blue-600 text-white rounded-lg hover:bg-blue-700 disabled:opacity-50 font-medium">
            {submitting ? 'Saving...' : 'Add Availability'}
          </button>
        </form>
      )}

      {availability.length === 0 ? (
        <div className="bg-white rounded-xl shadow-md p-12 text-center">
          <p className="text-gray-500 mb-3">No availability slots set.</p>
          <p className="text-sm text-gray-400">Click "+ Add Slot" above to set your hospital, days, and time slots so patients can book appointments.</p>
        </div>
      ) : (
        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4">
          {availability.map((a) => (
            <div key={a.id} className="bg-white rounded-xl shadow-md p-5">
              <div className="flex justify-between items-start">
                <div>
                  <h3 className="font-semibold text-gray-900">{a.dayOfWeek?.charAt(0) + a.dayOfWeek?.slice(1).toLowerCase()}</h3>
                  <p className="text-sm text-gray-500 mt-1">{a.hospitalName}</p>
                  <p className="text-sm text-blue-600 font-medium mt-1">{a.startTime} - {a.endTime}</p>
                  <p className="text-xs text-gray-500 mt-1">{a.slotDurationMinutes} min slots</p>
                </div>
                <button onClick={() => handleDelete(a.id)}
                  className="text-red-400 hover:text-red-600 text-sm">✕</button>
              </div>
            </div>
          ))}
        </div>
      )}
    </div>
  );
}

