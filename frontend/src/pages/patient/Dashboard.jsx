import { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import { getMyAppointments } from '../../api/appointmentApi';
import { getMyConsultations } from '../../api/consultationApi';
import { getMyPrescriptions } from '../../api/prescriptionApi';
import { getMyLabTests } from '../../api/labApi';
import StatusBadge from '../../components/StatusBadge';

export default function PatientDashboard() {
  const [appointments, setAppointments] = useState([]);
  const [consultations, setConsultations] = useState([]);
  const [prescriptions, setPrescriptions] = useState([]);
  const [labTests, setLabTests] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    Promise.all([
      getMyAppointments().catch(() => ({ data: [] })),
      getMyConsultations().catch(() => ({ data: [] })),
      getMyPrescriptions().catch(() => ({ data: [] })),
      getMyLabTests().catch(() => ({ data: [] })),
    ]).then(([a, c, p, l]) => {
      setAppointments(a.data);
      setConsultations(c.data);
      setPrescriptions(p.data);
      setLabTests(l.data);
      setLoading(false);
    });
  }, []);

  if (loading) return <div className="flex justify-center py-12"><div className="animate-spin rounded-full h-12 w-12 border-b-2 border-blue-600"></div></div>;

  const upcomingAppts = appointments.filter(a => ['REQUESTED', 'CONFIRMED'].includes(a.status)).slice(0, 5);

  return (
    <div className="space-y-6">
      <h1 className="text-2xl font-bold text-gray-900">Patient Dashboard</h1>

      <div className="grid grid-cols-1 md:grid-cols-4 gap-4">
        {[
          { label: 'Appointments', count: appointments.length, color: 'blue', to: '/patient/appointments' },
          { label: 'Consultations', count: consultations.length, color: 'green', to: '/patient/consultations' },
          { label: 'Prescriptions', count: prescriptions.length, color: 'purple', to: '/patient/prescriptions' },
          { label: 'Lab Reports', count: labTests.length, color: 'orange', to: '/patient/lab-reports' },
        ].map((item) => (
          <Link key={item.label} to={item.to} className="bg-white rounded-xl shadow-md p-6 hover:shadow-lg transition-shadow">
            <p className="text-sm text-gray-500">{item.label}</p>
            <p className="text-3xl font-bold text-gray-900 mt-1">{item.count}</p>
          </Link>
        ))}
      </div>

      <div className="grid grid-cols-1 lg:grid-cols-2 gap-6">
        <div className="bg-white rounded-xl shadow-md p-6">
          <div className="flex justify-between items-center mb-4">
            <h2 className="text-lg font-semibold">Upcoming Appointments</h2>
            <Link to="/patient/doctors" className="text-sm text-blue-600 hover:underline">Book New →</Link>
          </div>
          {upcomingAppts.length === 0 ? (
            <p className="text-gray-500 text-sm">No upcoming appointments</p>
          ) : (
            <div className="space-y-3">
              {upcomingAppts.map((a) => (
                <div key={a.id} className="flex justify-between items-center p-3 bg-gray-50 rounded-lg">
                  <div>
                    <p className="font-medium text-sm">{a.doctorName}</p>
                    <p className="text-xs text-gray-500">{a.appointmentDate} at {a.startTime}</p>
                    <p className="text-xs text-gray-500">{a.hospitalName}</p>
                  </div>
                  <StatusBadge status={a.status} />
                </div>
              ))}
            </div>
          )}
        </div>

        <div className="bg-white rounded-xl shadow-md p-6">
          <div className="flex justify-between items-center mb-4">
            <h2 className="text-lg font-semibold">Recent Prescriptions</h2>
            <Link to="/patient/prescriptions" className="text-sm text-blue-600 hover:underline">View All →</Link>
          </div>
          {prescriptions.length === 0 ? (
            <p className="text-gray-500 text-sm">No prescriptions yet</p>
          ) : (
            <div className="space-y-3">
              {prescriptions.slice(0, 5).map((p) => (
                <div key={p.id} className="flex justify-between items-center p-3 bg-gray-50 rounded-lg">
                  <div>
                    <p className="font-medium text-sm">By Dr. {p.doctorName}</p>
                    <p className="text-xs text-gray-500">{p.items?.length || 0} medication(s)</p>
                  </div>
                  <StatusBadge status={p.status} />
                </div>
              ))}
            </div>
          )}
        </div>
      </div>
    </div>
  );
}

