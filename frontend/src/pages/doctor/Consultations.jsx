import { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { getMyAppointments } from '../../api/appointmentApi';
import { createConsultation, getMyConsultations } from '../../api/consultationApi';

export default function DoctorConsultations() {
  const [consultations, setConsultations] = useState([]);
  const [completedAppts, setCompletedAppts] = useState([]);
  const [showForm, setShowForm] = useState(false);
  const [form, setForm] = useState({ appointmentId: '', symptoms: '', observations: '', diagnosis: '', clinicalNotes: '', treatmentPlan: '' });
  const [loading, setLoading] = useState(true);
  const [submitting, setSubmitting] = useState(false);
  const [error, setError] = useState('');

  const fetchData = async () => {
    try {
      const [c, a] = await Promise.all([
        getMyConsultations().catch(() => ({ data: [] })),
        getMyAppointments().catch(() => ({ data: [] })),
      ]);
      setConsultations(c.data);
      setCompletedAppts(a.data.filter(ap => ap.status === 'COMPLETED'));
    } catch (e) { console.error(e); }
    setLoading(false);
  };
  useEffect(() => { fetchData(); }, []);

  const handleChange = (e) => setForm({ ...form, [e.target.name]: e.target.value });

  const handleSubmit = async (e) => {
    e.preventDefault();
    setSubmitting(true);
    setError('');
    try {
      await createConsultation({ ...form, appointmentId: parseInt(form.appointmentId) });
      setShowForm(false);
      setForm({ appointmentId: '', symptoms: '', observations: '', diagnosis: '', clinicalNotes: '', treatmentPlan: '' });
      fetchData();
    } catch (err) {
      setError(err.response?.data?.message || 'Failed to create consultation');
    } finally {
      setSubmitting(false);
    }
  };

  if (loading) return <div className="flex justify-center py-12"><div className="animate-spin rounded-full h-12 w-12 border-b-2 border-blue-600"></div></div>;

  return (
    <div className="space-y-6">
      <div className="flex justify-between items-center">
        <h1 className="text-2xl font-bold text-gray-900">Consultations</h1>
        <button onClick={() => setShowForm(!showForm)}
          className="px-4 py-2 bg-blue-600 text-white rounded-lg hover:bg-blue-700 text-sm font-medium">
          {showForm ? 'Cancel' : '+ New Consultation'}
        </button>
      </div>

      {showForm && (
        <form onSubmit={handleSubmit} className="bg-white rounded-xl shadow-md p-6 space-y-4">
          {error && <div className="bg-red-50 text-red-700 px-4 py-3 rounded-lg text-sm">{error}</div>}
          <div>
            <label className="block text-sm font-medium text-gray-700 mb-1">Appointment</label>
            <select name="appointmentId" value={form.appointmentId} onChange={handleChange} required
              className="w-full px-3 py-2 border border-gray-300 rounded-lg">
              <option value="">Select completed appointment</option>
              {completedAppts.map(a => (
                <option key={a.id} value={a.id}>{a.patientName} — {a.appointmentDate} ({a.hospitalName})</option>
              ))}
            </select>
            {completedAppts.length === 0 && (
              <p className="text-xs text-amber-600 mt-1">No completed appointments yet. Mark an appointment as "Complete" on the Appointments page first.</p>
            )}
          </div>
          {['symptoms', 'observations', 'diagnosis', 'clinicalNotes', 'treatmentPlan'].map((field) => (
            <div key={field}>
              <label className="block text-sm font-medium text-gray-700 mb-1 capitalize">{field.replace(/([A-Z])/g, ' $1')}</label>
              <textarea name={field} value={form[field]} onChange={handleChange} rows={2}
                className="w-full px-3 py-2 border border-gray-300 rounded-lg" />
            </div>
          ))}
          <button type="submit" disabled={submitting}
            className="px-6 py-2.5 bg-blue-600 text-white rounded-lg hover:bg-blue-700 disabled:opacity-50 font-medium">
            {submitting ? 'Creating...' : 'Create Consultation'}
          </button>
        </form>
      )}

      {consultations.length === 0 ? (
        <div className="bg-white rounded-xl shadow-md p-12 text-center text-gray-500">No consultations yet</div>
      ) : (
        <div className="space-y-4">
          {consultations.map((c) => (
            <div key={c.id} className="bg-white rounded-xl shadow-md p-6">
              <div className="flex justify-between items-start">
                <div>
                  <h3 className="font-semibold text-gray-900">Patient: {c.patientName}</h3>
                  <p className="text-sm text-gray-500">{c.consultationDate}</p>
                </div>
              </div>
              <div className="mt-3 grid grid-cols-1 md:grid-cols-2 gap-3">
                {c.symptoms && <div><p className="text-xs font-medium text-gray-500 uppercase">Symptoms</p><p className="text-sm">{c.symptoms}</p></div>}
                {c.diagnosis && <div><p className="text-xs font-medium text-gray-500 uppercase">Diagnosis</p><p className="text-sm">{c.diagnosis}</p></div>}
                {c.observations && <div><p className="text-xs font-medium text-gray-500 uppercase">Observations</p><p className="text-sm">{c.observations}</p></div>}
                {c.treatmentPlan && <div><p className="text-xs font-medium text-gray-500 uppercase">Treatment Plan</p><p className="text-sm">{c.treatmentPlan}</p></div>}
              </div>
            </div>
          ))}
        </div>
      )}
    </div>
  );
}

