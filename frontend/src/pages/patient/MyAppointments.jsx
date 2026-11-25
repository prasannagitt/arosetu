import { useState, useEffect } from 'react';
import { getMyAppointments, cancelAppointment } from '../../api/appointmentApi';
import StatusBadge from '../../components/StatusBadge';

export default function MyAppointments() {
  const [appointments, setAppointments] = useState([]);
  const [loading, setLoading] = useState(true);

  const fetchData = async () => {
    try {
      const res = await getMyAppointments();
      setAppointments(res.data);
    } catch (err) { console.error(err); }
    setLoading(false);
  };

  useEffect(() => { fetchData(); }, []);

  const handleCancel = async (id) => {
    if (!confirm('Cancel this appointment?')) return;
    try { await cancelAppointment(id); fetchData(); } catch (err) { alert('Failed to cancel'); }
  };

  if (loading) return <div className="flex justify-center py-12"><div className="animate-spin rounded-full h-12 w-12 border-b-2 border-blue-600"></div></div>;

  return (
    <div className="space-y-6">
      <h1 className="text-2xl font-bold text-gray-900">My Appointments</h1>
      {appointments.length === 0 ? (
        <div className="bg-white rounded-xl shadow-md p-12 text-center text-gray-500">No appointments yet</div>
      ) : (
        <div className="space-y-4">
          {appointments.map((a) => (
            <div key={a.id} className="bg-white rounded-xl shadow-md p-6">
              <div className="flex justify-between items-start">
                <div>
                  <h3 className="font-semibold text-gray-900">Dr. {a.doctorName}</h3>
                  <p className="text-sm text-gray-500 mt-1">{a.hospitalName}</p>
                  <p className="text-sm text-gray-500">
                    {a.appointmentDate} • {a.startTime} - {a.endTime}
                  </p>
                  {a.reason && <p className="text-sm text-gray-600 mt-2">Reason: {a.reason}</p>}
                </div>
                <div className="flex items-center space-x-2">
                  <StatusBadge status={a.status} />
                  {['REQUESTED', 'CONFIRMED'].includes(a.status) && (
                    <button onClick={() => handleCancel(a.id)}
                      className="px-3 py-1 text-xs bg-red-50 text-red-600 rounded-lg hover:bg-red-100">
                      Cancel
                    </button>
                  )}
                </div>
              </div>
            </div>
          ))}
        </div>
      )}
    </div>
  );
}

