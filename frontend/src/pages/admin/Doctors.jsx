import { useState, useEffect } from 'react';
import { getPendingDoctors, approveDoctor, rejectDoctor } from '../../api/adminApi';
import { getAllDoctors } from '../../api/doctorApi';
import StatusBadge from '../../components/StatusBadge';

export default function AdminDoctors() {
  const [pending, setPending] = useState([]);
  const [allDoctors, setAllDoctors] = useState([]);
  const [loading, setLoading] = useState(true);
  const [tab, setTab] = useState('pending');

  const fetchData = async () => {
    try {
      const [p, a] = await Promise.all([getPendingDoctors(), getAllDoctors()]);
      setPending(p.data);
      setAllDoctors(a.data);
    } catch (e) { console.error(e); }
    setLoading(false);
  };
  useEffect(() => { fetchData(); }, []);

  const handleAction = async (id, action) => {
    try {
      if (action === 'approve') await approveDoctor(id);
      else await rejectDoctor(id);
      fetchData();
    } catch (e) { alert('Failed'); }
  };

  if (loading) return <div className="flex justify-center py-12"><div className="animate-spin rounded-full h-12 w-12 border-b-2 border-blue-600"></div></div>;

  return (
    <div className="space-y-6">
      <h1 className="text-2xl font-bold text-gray-900">Manage Doctors</h1>
      <div className="flex gap-2">
        <button onClick={() => setTab('pending')} className={`px-4 py-2 rounded-lg text-sm font-medium ${tab === 'pending' ? 'bg-blue-600 text-white' : 'bg-white text-gray-700 border'}`}>
          Pending ({pending.length})
        </button>
        <button onClick={() => setTab('all')} className={`px-4 py-2 rounded-lg text-sm font-medium ${tab === 'all' ? 'bg-blue-600 text-white' : 'bg-white text-gray-700 border'}`}>
          All Doctors ({allDoctors.length})
        </button>
      </div>

      {tab === 'pending' ? (
        pending.length === 0 ? (
          <div className="bg-white rounded-xl shadow-md p-12 text-center text-gray-500">No pending approvals</div>
        ) : (
          <div className="space-y-4">
            {pending.map((d) => (
              <div key={d.id} className="bg-white rounded-xl shadow-md p-6">
                <div className="flex justify-between items-start">
                  <div>
                    <h3 className="font-semibold text-gray-900">{d.user?.name || 'Doctor'}</h3>
                    <p className="text-sm text-blue-600">{d.specialization}</p>
                    <p className="text-sm text-gray-500">{d.qualification} • {d.experienceYears} yrs</p>
                    <p className="text-sm text-gray-500">License: {d.licenseNumber}</p>
                  </div>
                  <StatusBadge status={d.approvalStatus} />
                </div>
                <div className="mt-4 flex gap-2">
                  <button onClick={() => handleAction(d.id, 'approve')} className="px-4 py-2 text-sm bg-green-600 text-white rounded-lg hover:bg-green-700">Approve</button>
                  <button onClick={() => handleAction(d.id, 'reject')} className="px-4 py-2 text-sm bg-red-600 text-white rounded-lg hover:bg-red-700">Reject</button>
                </div>
              </div>
            ))}
          </div>
        )
      ) : (
        <div className="bg-white rounded-xl shadow-md overflow-hidden">
          <table className="min-w-full divide-y divide-gray-200">
            <thead className="bg-gray-50">
              <tr>
                <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase">Name</th>
                <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase">Specialization</th>
                <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase">Experience</th>
                <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase">Status</th>
              </tr>
            </thead>
            <tbody className="divide-y divide-gray-200">
              {allDoctors.map((d) => (
                <tr key={d.id}>
                  <td className="px-6 py-4 text-sm text-gray-900">{d.name}</td>
                  <td className="px-6 py-4 text-sm text-gray-500">{d.specialization}</td>
                  <td className="px-6 py-4 text-sm text-gray-500">{d.experienceYears} yrs</td>
                  <td className="px-6 py-4 text-sm"><StatusBadge status={d.approvalStatus || 'APPROVED'} /></td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      )}
    </div>
  );
}

