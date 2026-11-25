import { useState, useEffect } from 'react';
import { getActiveLaboratories } from '../../api/laboratoryApi';
import { getByLaboratory, acceptTest, collectSample, processTest, completeTest } from '../../api/labApi';
import StatusBadge from '../../components/StatusBadge';

export default function LabDashboard() {
  const [laboratories, setLaboratories] = useState([]);
  const [selectedLab, setSelectedLab] = useState('');
  const [tests, setTests] = useState([]);
  const [loading, setLoading] = useState(true);
  const [testsLoading, setTestsLoading] = useState(false);
  const [completeForm, setCompleteForm] = useState({ testId: null, resultSummary: '', reportFileUrl: '' });

  useEffect(() => {
    getActiveLaboratories().then(r => setLaboratories(r.data)).catch(console.error).finally(() => setLoading(false));
  }, []);

  const fetchTests = async (labId) => {
    setTestsLoading(true);
    try {
      const res = await getByLaboratory(labId);
      setTests(res.data);
    } catch (e) { console.error(e); }
    setTestsLoading(false);
  };

  useEffect(() => { if (selectedLab) fetchTests(selectedLab); }, [selectedLab]);

  const handleAction = async (id, action) => {
    try {
      if (action === 'accept') await acceptTest(id);
      else if (action === 'collect') await collectSample(id);
      else if (action === 'process') await processTest(id);
      fetchTests(selectedLab);
    } catch (e) { alert('Action failed: ' + (e.response?.data?.message || e.message)); }
  };

  const handleComplete = async (e) => {
    e.preventDefault();
    try {
      await completeTest(completeForm.testId, completeForm.resultSummary, completeForm.reportFileUrl);
      setCompleteForm({ testId: null, resultSummary: '', reportFileUrl: '' });
      fetchTests(selectedLab);
    } catch (e) { alert('Failed to complete test'); }
  };

  if (loading) return <div className="flex justify-center py-12"><div className="animate-spin rounded-full h-12 w-12 border-b-2 border-blue-600"></div></div>;

  const statusOrder = ['REQUESTED', 'ACCEPTED', 'SAMPLE_COLLECTED', 'PROCESSING', 'COMPLETED'];

  return (
    <div className="space-y-6">
      <h1 className="text-2xl font-bold text-gray-900">Lab Technician Dashboard</h1>

      <div className="bg-white rounded-xl shadow-md p-6">
        <label className="block text-sm font-medium text-gray-700 mb-2">Select Laboratory</label>
        <select value={selectedLab} onChange={(e) => setSelectedLab(e.target.value)}
          className="w-full max-w-md px-3 py-2 border border-gray-300 rounded-lg">
          <option value="">Choose a laboratory</option>
          {laboratories.map(l => <option key={l.id} value={l.id}>{l.name} — {l.address}</option>)}
        </select>
      </div>

      {selectedLab && (
        <>
          {testsLoading ? (
            <div className="flex justify-center py-8"><div className="animate-spin rounded-full h-8 w-8 border-b-2 border-blue-600"></div></div>
          ) : tests.length === 0 ? (
            <div className="bg-white rounded-xl shadow-md p-12 text-center text-gray-500">No lab tests for this laboratory</div>
          ) : (
            <div className="space-y-4">
              {statusOrder.map(status => {
                const filtered = tests.filter(t => t.status === status);
                if (filtered.length === 0) return null;
                return (
                  <div key={status}>
                    <h2 className="text-lg font-semibold text-gray-700 mb-3">{status.replace(/_/g, ' ')} ({filtered.length})</h2>
                    <div className="space-y-3">
                      {filtered.map(t => (
                        <div key={t.id} className="bg-white rounded-xl shadow-md p-5">
                          <div className="flex justify-between items-start">
                            <div>
                              <h3 className="font-semibold text-gray-900">{t.testName}</h3>
                              <p className="text-sm text-gray-500">Patient: {t.patientName || 'N/A'}</p>
                              <p className="text-sm text-gray-500">Doctor: Dr. {t.doctorName}</p>
                              {t.instructions && <p className="text-sm text-gray-600 mt-1">Instructions: {t.instructions}</p>}
                            </div>
                            <StatusBadge status={t.status} />
                          </div>

                          {t.status === 'COMPLETED' && t.resultSummary && (
                            <div className="mt-3 p-3 bg-green-50 rounded-lg">
                              <p className="text-xs font-medium text-green-800 uppercase">Result</p>
                              <p className="text-sm text-green-900">{t.resultSummary}</p>
                            </div>
                          )}

                          <div className="mt-3 flex flex-wrap gap-2">
                            {t.status === 'REQUESTED' && (
                              <button onClick={() => handleAction(t.id, 'accept')} className="px-3 py-1.5 text-sm bg-blue-600 text-white rounded-lg hover:bg-blue-700">Accept</button>
                            )}
                            {t.status === 'ACCEPTED' && (
                              <button onClick={() => handleAction(t.id, 'collect')} className="px-3 py-1.5 text-sm bg-purple-600 text-white rounded-lg hover:bg-purple-700">Collect Sample</button>
                            )}
                            {t.status === 'SAMPLE_COLLECTED' && (
                              <button onClick={() => handleAction(t.id, 'process')} className="px-3 py-1.5 text-sm bg-orange-600 text-white rounded-lg hover:bg-orange-700">Start Processing</button>
                            )}
                            {t.status === 'PROCESSING' && (
                              <button onClick={() => setCompleteForm({ ...completeForm, testId: t.id })} className="px-3 py-1.5 text-sm bg-green-600 text-white rounded-lg hover:bg-green-700">Enter Results</button>
                            )}
                          </div>

                          {completeForm.testId === t.id && (
                            <form onSubmit={handleComplete} className="mt-4 p-4 bg-gray-50 rounded-lg space-y-3">
                              <div>
                                <label className="block text-sm font-medium text-gray-700 mb-1">Result Summary *</label>
                                <textarea value={completeForm.resultSummary} onChange={(e) => setCompleteForm({ ...completeForm, resultSummary: e.target.value })}
                                  required rows={3} className="w-full px-3 py-2 border border-gray-300 rounded-lg text-sm"
                                  placeholder="Enter test results..." />
                              </div>
                              <div>
                                <label className="block text-sm font-medium text-gray-700 mb-1">Report File URL (optional)</label>
                                <input value={completeForm.reportFileUrl} onChange={(e) => setCompleteForm({ ...completeForm, reportFileUrl: e.target.value })}
                                  className="w-full px-3 py-2 border border-gray-300 rounded-lg text-sm" placeholder="https://..." />
                              </div>
                              <div className="flex gap-2">
                                <button type="submit" className="px-4 py-2 text-sm bg-green-600 text-white rounded-lg hover:bg-green-700">Complete Test</button>
                                <button type="button" onClick={() => setCompleteForm({ testId: null, resultSummary: '', reportFileUrl: '' })}
                                  className="px-4 py-2 text-sm bg-gray-200 text-gray-700 rounded-lg hover:bg-gray-300">Cancel</button>
                              </div>
                            </form>
                          )}
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

