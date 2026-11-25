import api from './axios';
export const createLabTest = (data) => api.post('/lab-tests', data);
export const getMyLabTests = () => api.get('/lab-tests/me');
export const getLabTestById = (id) => api.get(`/lab-tests/${id}`);
export const getByLaboratory = (labId) => api.get(`/lab-tests/laboratory/${labId}`);
export const acceptTest = (id) => api.patch(`/lab-tests/${id}/accept`);
export const collectSample = (id) => api.patch(`/lab-tests/${id}/collect-sample`);
export const processTest = (id) => api.patch(`/lab-tests/${id}/process`);
export const completeTest = (id, resultSummary, reportFileUrl) =>
  api.patch(`/lab-tests/${id}/complete`, null, {
    params: { resultSummary, ...(reportFileUrl ? { reportFileUrl } : {}) }
  });

