import { useState, useEffect } from 'react';
import { getMyPrescriptions } from '../../api/prescriptionApi';
import StatusBadge from '../../components/StatusBadge';

export default function MyPrescriptions() {
  const [prescriptions, setPrescriptions] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    getMyPrescriptions().then(r => setPrescriptions(r.data)).catch(console.error).finally(() => setLoading(false));
  }, []);

  if (loading) return <div className="flex justify-center py-12"><div className="animate-spin rounded-full h-12 w-12 border-b-2 border-blue-600"></div></div>;

  return (
    <div className="space-y-6">
      <h1 className="text-2xl font-bold text-gray-900">My Prescriptions</h1>
      {prescriptions.length === 0 ? (
        <div className="bg-white rounded-xl shadow-md p-12 text-center text-gray-500">No prescriptions yet</div>
      ) : (
        <div className="space-y-4">
          {prescriptions.map((p) => (
            <div key={p.id} className="bg-white rounded-xl shadow-md p-6">
              <div className="flex justify-between items-start mb-4">
                <div>
                  <h3 className="font-semibold text-gray-900">Prescription #{p.id}</h3>
                  <p className="text-sm text-gray-500">Dr. {p.doctorName}</p>
                  {p.pharmacyName && <p className="text-sm text-gray-500">Pharmacy: {p.pharmacyName}</p>}
                </div>
                <StatusBadge status={p.status} />
              </div>
              {p.notes && <p className="text-sm text-gray-600 mb-3">{p.notes}</p>}
              {p.items && p.items.length > 0 && (
                <div className="border rounded-lg overflow-hidden">
                  <table className="min-w-full divide-y divide-gray-200">
                    <thead className="bg-gray-50">
                      <tr>
                        <th className="px-4 py-2 text-left text-xs font-medium text-gray-500">Medicine</th>
                        <th className="px-4 py-2 text-left text-xs font-medium text-gray-500">Dosage</th>
                        <th className="px-4 py-2 text-left text-xs font-medium text-gray-500">Frequency</th>
                        <th className="px-4 py-2 text-left text-xs font-medium text-gray-500">Duration</th>
                      </tr>
                    </thead>
                    <tbody className="divide-y divide-gray-200">
                      {p.items.map((item, i) => (
                        <tr key={item.id || i}>
                          <td className="px-4 py-2 text-sm text-gray-900">{item.medicineName}</td>
                          <td className="px-4 py-2 text-sm text-gray-500">{item.dosage}</td>
                          <td className="px-4 py-2 text-sm text-gray-500">{item.frequency}</td>
                          <td className="px-4 py-2 text-sm text-gray-500">{item.duration}</td>
                        </tr>
                      ))}
                    </tbody>
                  </table>
                </div>
              )}
            </div>
          ))}
        </div>
      )}
    </div>
  );
}

