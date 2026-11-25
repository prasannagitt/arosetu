import { useState, useEffect } from 'react';
import { getActivePharmacies } from '../../api/pharmacyApi';
import { getByPharmacy, markReady, markDispensed } from '../../api/prescriptionApi';
import StatusBadge from '../../components/StatusBadge';

export default function PharmacistDashboard() {
  const [pharmacies, setPharmacies] = useState([]);
  const [selectedPharmacy, setSelectedPharmacy] = useState('');
  const [prescriptions, setPrescriptions] = useState([]);
  const [loading, setLoading] = useState(true);
  const [prescLoading, setPrescLoading] = useState(false);

  useEffect(() => {
    getActivePharmacies().then(r => setPharmacies(r.data)).catch(console.error).finally(() => setLoading(false));
  }, []);

  const fetchPrescriptions = async (id) => {
    setPrescLoading(true);
    try {
      const res = await getByPharmacy(id);
      setPrescriptions(res.data);
    } catch (e) { console.error(e); }
    setPrescLoading(false);
  };

  useEffect(() => { if (selectedPharmacy) fetchPrescriptions(selectedPharmacy); }, [selectedPharmacy]);

  const handleAction = async (id, action) => {
    try {
      if (action === 'ready') await markReady(id);
      else if (action === 'dispense') await markDispensed(id);
      fetchPrescriptions(selectedPharmacy);
    } catch (e) { alert('Action failed: ' + (e.response?.data?.message || e.message)); }
  };

  if (loading) return <div className="flex justify-center py-12"><div className="animate-spin rounded-full h-12 w-12 border-b-2 border-blue-600"></div></div>;

  const statusOrder = ['SENT_TO_PHARMACY', 'READY', 'DISPENSED'];

  return (
    <div className="space-y-6">
      <h1 className="text-2xl font-bold text-gray-900">Pharmacist Dashboard</h1>

      <div className="bg-white rounded-xl shadow-md p-6">
        <label className="block text-sm font-medium text-gray-700 mb-2">Select Pharmacy</label>
        <select value={selectedPharmacy} onChange={(e) => setSelectedPharmacy(e.target.value)}
          className="w-full max-w-md px-3 py-2 border border-gray-300 rounded-lg">
          <option value="">Choose a pharmacy</option>
          {pharmacies.map(p => <option key={p.id} value={p.id}>{p.name} — {p.address}</option>)}
        </select>
      </div>

      {selectedPharmacy && (
        <>
          {prescLoading ? (
            <div className="flex justify-center py-8"><div className="animate-spin rounded-full h-8 w-8 border-b-2 border-blue-600"></div></div>
          ) : prescriptions.length === 0 ? (
            <div className="bg-white rounded-xl shadow-md p-12 text-center text-gray-500">No prescriptions for this pharmacy</div>
          ) : (
            <div className="space-y-6">
              {statusOrder.map(status => {
                const filtered = prescriptions.filter(p => p.status === status);
                if (filtered.length === 0) return null;
                return (
                  <div key={status}>
                    <h2 className="text-lg font-semibold text-gray-700 mb-3">{status.replace(/_/g, ' ')} ({filtered.length})</h2>
                    <div className="space-y-3">
                      {filtered.map(p => (
                        <div key={p.id} className="bg-white rounded-xl shadow-md p-5">
                          <div className="flex justify-between items-start mb-3">
                            <div>
                              <h3 className="font-semibold text-gray-900">Prescription #{p.id}</h3>
                              <p className="text-sm text-gray-500">Patient: {p.patientName}</p>
                              <p className="text-sm text-gray-500">Doctor: Dr. {p.doctorName}</p>
                              {p.notes && <p className="text-sm text-gray-600 mt-1">{p.notes}</p>}
                            </div>
                            <StatusBadge status={p.status} />
                          </div>

                          {p.items?.length > 0 && (
                            <div className="border rounded-lg overflow-hidden mb-3">
                              <table className="min-w-full divide-y divide-gray-200">
                                <thead className="bg-gray-50">
                                  <tr>
                                    <th className="px-4 py-2 text-left text-xs font-medium text-gray-500">Medicine</th>
                                    <th className="px-4 py-2 text-left text-xs font-medium text-gray-500">Dosage</th>
                                    <th className="px-4 py-2 text-left text-xs font-medium text-gray-500">Frequency</th>
                                    <th className="px-4 py-2 text-left text-xs font-medium text-gray-500">Duration</th>
                                    <th className="px-4 py-2 text-left text-xs font-medium text-gray-500">Instructions</th>
                                  </tr>
                                </thead>
                                <tbody className="divide-y divide-gray-200">
                                  {p.items.map((item, i) => (
                                    <tr key={item.id || i}>
                                      <td className="px-4 py-2 text-sm font-medium text-gray-900">{item.medicineName}</td>
                                      <td className="px-4 py-2 text-sm text-gray-500">{item.dosage}</td>
                                      <td className="px-4 py-2 text-sm text-gray-500">{item.frequency}</td>
                                      <td className="px-4 py-2 text-sm text-gray-500">{item.duration}</td>
                                      <td className="px-4 py-2 text-sm text-gray-500">{item.instructions}</td>
                                    </tr>
                                  ))}
                                </tbody>
                              </table>
                            </div>
                          )}

                          <div className="flex gap-2">
                            {p.status === 'SENT_TO_PHARMACY' && (
                              <button onClick={() => handleAction(p.id, 'ready')} className="px-4 py-2 text-sm bg-blue-600 text-white rounded-lg hover:bg-blue-700">Mark Ready</button>
                            )}
                            {p.status === 'READY' && (
                              <button onClick={() => handleAction(p.id, 'dispense')} className="px-4 py-2 text-sm bg-green-600 text-white rounded-lg hover:bg-green-700">Dispense</button>
                            )}
                          </div>
                        </div>
                      ))}
                    </div>
                  </div>
                );
              })}
            </div>
          )}
        </>
      )}
    </div>
  );
}

