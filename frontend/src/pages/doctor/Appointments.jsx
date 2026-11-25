import { useState, useEffect } from 'react';
import { getMyAppointments, confirmAppointment, rejectAppointment, cancelAppointment, completeAppointment } from '../../api/appointmentApi';
import StatusBadge from '../../components/StatusBadge';

export default function DoctorAppointments() {
  const [appointments, setAppointments] = useState([]);
  const [loading, setLoading] = useState(true);
  const [filter, setFilter] = useState('ALL');

  const fetchData = async () => {
    try { const r = await getMyAppointments(); setAppointments(r.data); } catch (e) { console.error(e); }
    setLoading(false);
  };
  useEffect(() => { fetchData(); }, []);

  const handleAction = async (id, action) => {
    try {
      if (action === 'confirm') await confirmAppointment(id);
      else if (action === 'reject') await rejectAppointment(id);
      else if (action === 'cancel') await cancelAppointment(id);
      else if (action === 'complete') await completeAppointment(id);
      fetchData();
    } catch (e) { alert('Action failed: ' + (e.response?.data?.message || e.message)); }
  };

  const filtered = filter === 'ALL' ? appointments : appointments.filter(a => a.status === filter);

  if (loading) return <div className="flex justify-center py-12"><div className="animate-spin rounded-full h-12 w-12 border-b-2 border-blue-600"></div></div>;

  return (
    <div className="space-y-6">
      <h1 className="text-2xl font-bold text-gray-900">Appointments</h1>
      <div className="flex flex-wrap gap-2">
        {['ALL', 'REQUESTED', 'CONFIRMED', 'COMPLETED', 'CANCELLED', 'REJECTED'].map((f) => (
          <button key={f} onClick={() => setFilter(f)}
            className={`px-3 py-1.5 rounded-lg text-sm font-medium ${filter === f ? 'bg-blue-600 text-white' : 'bg-white text-gray-700 border border-gray-300 hover:bg-gray-50'}`}>
            {f === 'ALL' ? 'All' : f.charAt(0) + f.slice(1).toLowerCase()}
          </button>
        ))}
      </div>

      {filtered.length === 0 ? (
        <div className="bg-white rounded-xl shadow-md p-12 text-center text-gray-500">No appointments found</div>
      ) : (
        <div className="space-y-4">
          {filtered.map((a) => (
            <div key={a.id} className="bg-white rounded-xl shadow-md p-6">
              <div className="flex justify-between items-start">
                <div>
                  <h3 className="font-semibold text-gray-900">{a.patientName}</h3>
                  <p className="text-sm text-gray-500">{a.hospitalName}</p>
                  <p className="text-sm text-gray-500">{a.appointmentDate} • {a.startTime} - {a.endTime}</p>
                  {a.reason && <p className="text-sm text-gray-600 mt-1">Reason: {a.reason}</p>}
                </div>
                <StatusBadge status={a.status} />
              </div>
              <div className="mt-4 flex flex-wrap gap-2">
                {a.status === 'REQUESTED' && (
                  <>
                    <button onClick={() => handleAction(a.id, 'confirm')} className="px-3 py-1.5 text-sm bg-green-600 text-white rounded-lg hover:bg-green-700">Confirm</button>
                    <button onClick={() => handleAction(a.id, 'reject')} className="px-3 py-1.5 text-sm bg-red-600 text-white rounded-lg hover:bg-red-700">Reject</button>
                  </>
                )}
                {a.status === 'CONFIRMED' && (
                  <>
                    <button onClick={() => handleAction(a.id, 'complete')} className="px-3 py-1.5 text-sm bg-green-600 text-white rounded-lg hover:bg-green-700">Mark Complete</button>
                    <button onClick={() => handleAction(a.id, 'cancel')} className="px-3 py-1.5 text-sm bg-red-600 text-white rounded-lg hover:bg-red-700">Cancel</button>
                  </>
                )}
              </div>
            </div>
          ))}
        </div>
      )}
    </div>
  );
}

