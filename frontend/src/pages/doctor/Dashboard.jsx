import { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import { getMyAppointments } from '../../api/appointmentApi';
import { getMyConsultations } from '../../api/consultationApi';
import { getMyDoctorProfile } from '../../api/doctorApi';
import StatusBadge from '../../components/StatusBadge';

export default function DoctorDashboard() {
  const [appointments, setAppointments] = useState([]);
  const [consultations, setConsultations] = useState([]);
  const [profile, setProfile] = useState(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    Promise.all([
      getMyAppointments().catch(() => ({ data: [] })),
      getMyConsultations().catch(() => ({ data: [] })),
      getMyDoctorProfile().catch(() => ({ data: null })),
    ]).then(([a, c, p]) => {
      setAppointments(a.data);
      setConsultations(c.data);
      setProfile(p.data);
      setLoading(false);
    });
  }, []);

  if (loading) return <div className="flex justify-center py-12"><div className="animate-spin rounded-full h-12 w-12 border-b-2 border-blue-600"></div></div>;

  const pendingAppts = appointments.filter(a => a.status === 'REQUESTED');
  const confirmedAppts = appointments.filter(a => a.status === 'CONFIRMED');

  return (
    <div className="space-y-6">
      <h1 className="text-2xl font-bold text-gray-900">Doctor Dashboard</h1>

      {profile?.approvalStatus === 'PENDING' && (
        <div className="bg-yellow-50 border border-yellow-200 text-yellow-800 p-4 rounded-xl">
          Your profile is pending admin approval. You won't be visible in search results until approved.
        </div>
      )}

      <div className="grid grid-cols-1 md:grid-cols-4 gap-4">
        {[
          { label: 'Pending Requests', count: pendingAppts.length, to: '/doctor/appointments' },
          { label: 'Confirmed Today', count: confirmedAppts.length, to: '/doctor/appointments' },
          { label: 'Total Consultations', count: consultations.length, to: '/doctor/consultations' },
          { label: 'Total Appointments', count: appointments.length, to: '/doctor/appointments' },
        ].map((item) => (
          <Link key={item.label} to={item.to} className="bg-white rounded-xl shadow-md p-6 hover:shadow-lg transition-shadow">
            <p className="text-sm text-gray-500">{item.label}</p>
            <p className="text-3xl font-bold text-gray-900 mt-1">{item.count}</p>
          </Link>
        ))}
      </div>

      <div className="bg-white rounded-xl shadow-md p-6">
        <h2 className="text-lg font-semibold mb-4">Pending Appointment Requests</h2>
        {pendingAppts.length === 0 ? (
          <p className="text-gray-500 text-sm">No pending requests</p>
        ) : (
          <div className="space-y-3">
            {pendingAppts.slice(0, 10).map((a) => (
              <div key={a.id} className="flex justify-between items-center p-3 bg-gray-50 rounded-lg">
                <div>
                  <p className="font-medium text-sm">{a.patientName}</p>
                  <p className="text-xs text-gray-500">{a.appointmentDate} at {a.startTime}</p>
                  <p className="text-xs text-gray-500">{a.hospitalName}</p>
                  {a.reason && <p className="text-xs text-gray-500">Reason: {a.reason}</p>}
                </div>
                <StatusBadge status={a.status} />
              </div>
            ))}
          </div>
        )}
      </div>
    </div>
  );
}

