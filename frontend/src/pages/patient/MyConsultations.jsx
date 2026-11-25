import { useState, useEffect } from 'react';
import { getMyConsultations } from '../../api/consultationApi';

export default function MyConsultations() {
  const [consultations, setConsultations] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    getMyConsultations().then(r => setConsultations(r.data)).catch(console.error).finally(() => setLoading(false));
  }, []);

  if (loading) return <div className="flex justify-center py-12"><div className="animate-spin rounded-full h-12 w-12 border-b-2 border-blue-600"></div></div>;

  return (
    <div className="space-y-6">
      <h1 className="text-2xl font-bold text-gray-900">My Consultations</h1>
      {consultations.length === 0 ? (
        <div className="bg-white rounded-xl shadow-md p-12 text-center text-gray-500">No consultations yet</div>
      ) : (
        <div className="space-y-4">
          {consultations.map((c) => (
            <div key={c.id} className="bg-white rounded-xl shadow-md p-6">
              <div className="flex justify-between items-start">
                <div>
                  <h3 className="font-semibold text-gray-900">Dr. {c.doctorName}</h3>
                  <p className="text-sm text-gray-500">{c.consultationDate}</p>
                </div>
              </div>
              <div className="mt-4 grid grid-cols-1 md:grid-cols-2 gap-4">
                {c.symptoms && (
                  <div><p className="text-xs font-medium text-gray-500 uppercase">Symptoms</p><p className="text-sm text-gray-700">{c.symptoms}</p></div>
                )}
                {c.diagnosis && (
                  <div><p className="text-xs font-medium text-gray-500 uppercase">Diagnosis</p><p className="text-sm text-gray-700">{c.diagnosis}</p></div>
                )}
                {c.observations && (
                  <div><p className="text-xs font-medium text-gray-500 uppercase">Observations</p><p className="text-sm text-gray-700">{c.observations}</p></div>
                )}
                {c.treatmentPlan && (
                  <div><p className="text-xs font-medium text-gray-500 uppercase">Treatment Plan</p><p className="text-sm text-gray-700">{c.treatmentPlan}</p></div>
                )}
              </div>
              {c.clinicalNotes && (
                <div className="mt-3 p-3 bg-gray-50 rounded-lg">
                  <p className="text-xs font-medium text-gray-500 uppercase">Clinical Notes</p>
                  <p className="text-sm text-gray-700">{c.clinicalNotes}</p>
                </div>
              )}
            </div>
          ))}
        </div>
      )}
    </div>
  );
}

