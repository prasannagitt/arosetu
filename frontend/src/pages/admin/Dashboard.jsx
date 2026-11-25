import { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import { getStats } from '../../api/adminApi';

export default function AdminDashboard() {
  const [stats, setStats] = useState({});
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    getStats().then(r => setStats(r.data)).catch(console.error).finally(() => setLoading(false));
  }, []);

  if (loading) return <div className="flex justify-center py-12"><div className="animate-spin rounded-full h-12 w-12 border-b-2 border-blue-600"></div></div>;

  const cards = [
    { label: 'Total Users', value: stats.totalUsers || 0, to: '/admin/users', icon: '👤', color: 'blue' },
    { label: 'Total Doctors', value: stats.totalDoctors || 0, to: '/admin/doctors', icon: '🩺', color: 'green' },
    { label: 'Total Patients', value: stats.totalPatients || 0, to: '/admin/users', icon: '🏥', color: 'purple' },
    { label: 'Total Hospitals', value: stats.totalHospitals || 0, to: '/admin/hospitals', icon: '🏨', color: 'orange' },
    { label: 'Total Laboratories', value: stats.totalLaboratories || 0, to: '/admin/laboratories', icon: '🔬', color: 'pink' },
    { label: 'Total Pharmacies', value: stats.totalPharmacies || 0, to: '/admin/pharmacies', icon: '💊', color: 'teal' },
  ];

  return (
    <div className="space-y-6">
      <h1 className="text-2xl font-bold text-gray-900">Admin Dashboard</h1>
      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4">
        {cards.map((card) => (
          <Link key={card.label} to={card.to} className="bg-white rounded-xl shadow-md p-6 hover:shadow-lg transition-shadow">
            <div className="flex items-center justify-between">
              <div>
                <p className="text-sm text-gray-500">{card.label}</p>
                <p className="text-3xl font-bold text-gray-900 mt-1">{card.value}</p>
              </div>
              <span className="text-3xl">{card.icon}</span>
            </div>
          </Link>
        ))}
      </div>
    </div>
  );
}

