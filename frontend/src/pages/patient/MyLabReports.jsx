import { useState, useEffect } from 'react';
import { getMyLabTests } from '../../api/labApi';
import StatusBadge from '../../components/StatusBadge';

export default function MyLabReports() {
  const [tests, setTests] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    getMyLabTests().then(r => setTests(r.data)).catch(console.error).finally(() => setLoading(false));
  }, []);

  if (loading) return <div className="flex justify-center py-12"><div className="animate-spin rounded-full h-12 w-12 border-b-2 border-blue-600"></div></div>;

  return (
    <div className="space-y-6">
      <h1 className="text-2xl font-bold text-gray-900">My Lab Reports</h1>
      {tests.length === 0 ? (
        <div className="bg-white rounded-xl shadow-md p-12 text-center text-gray-500">No lab tests yet</div>
      ) : (
        <div className="space-y-4">
          {tests.map((t) => (
            <div key={t.id} className="bg-white rounded-xl shadow-md p-6">
              <div className="flex justify-between items-start">
                <div>
                  <h3 className="font-semibold text-gray-900">{t.testName}</h3>
                  <p className="text-sm text-gray-500">Ordered by Dr. {t.doctorName}</p>
                  <p className="text-sm text-gray-500">Lab: {t.laboratoryName}</p>
                  {t.instructions && <p className="text-sm text-gray-600 mt-1">Instructions: {t.instructions}</p>}
                </div>
                <StatusBadge status={t.status} />
              </div>
              {t.status === 'COMPLETED' && (
                <div className="mt-4 p-4 bg-green-50 rounded-lg">
                  <p className="text-xs font-medium text-green-800 uppercase mb-1">Result Summary</p>
                  <p className="text-sm text-green-900">{t.resultSummary}</p>
                  {t.reportFileUrl && (
                    <a href={t.reportFileUrl} target="_blank" rel="noopener noreferrer"
                      className="inline-block mt-2 text-sm text-blue-600 hover:underline">View Full Report →</a>
                  )}
                </div>
              )}
            </div>
          ))}
        </div>
      )}
    </div>
  );
}

