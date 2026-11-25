import { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { searchDoctors } from '../../api/doctorApi';


export default function DoctorSearch() {
  const [doctors, setDoctors] = useState([]);
  const [specialization, setSpecialization] = useState('');
  const [city, setCity] = useState('');
  const [loading, setLoading] = useState(false);
  const navigate = useNavigate();

  const handleSearch = async () => {
    setLoading(true);
    try {
      const params = {};
      if (specialization) params.specialization = specialization;
      if (city) params.city = city;
      const res = await searchDoctors(params);
      setDoctors(res.data);
    } catch (err) {
      console.error(err);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => { handleSearch(); }, []);

  return (
    <div className="space-y-6">
      <h1 className="text-2xl font-bold text-gray-900">Find a Doctor</h1>

      <div className="bg-white rounded-xl shadow-md p-6">
        <div className="flex flex-col md:flex-row gap-4">
          <input
            type="text" placeholder="Specialization (e.g., Cardiology)"
            value={specialization} onChange={(e) => setSpecialization(e.target.value)}
            className="flex-1 px-3 py-2 border border-gray-300 rounded-lg focus:ring-blue-500 focus:border-blue-500"
          />
          <input
            type="text" placeholder="City"
            value={city} onChange={(e) => setCity(e.target.value)}
            className="flex-1 px-3 py-2 border border-gray-300 rounded-lg focus:ring-blue-500 focus:border-blue-500"
          />
          <button onClick={handleSearch} disabled={loading}
            className="px-6 py-2 bg-blue-600 text-white rounded-lg hover:bg-blue-700 disabled:opacity-50">
            {loading ? 'Searching...' : 'Search'}
          </button>
        </div>
      </div>

      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4">
        {doctors.map((doc) => (
          <div key={doc.id} className="bg-white rounded-xl shadow-md p-6 hover:shadow-lg transition-shadow">
            <h3 className="text-lg font-semibold text-gray-900">{doc.name}</h3>
            <p className="text-blue-600 text-sm font-medium mt-1">{doc.specialization}</p>
            <p className="text-gray-500 text-sm mt-1">{doc.qualification}</p>
            <p className="text-gray-500 text-sm">{doc.experienceYears} years experience</p>
            {doc.consultationFee && (
              <p className="text-gray-700 text-sm mt-2 font-medium">₹{doc.consultationFee} consultation fee</p>
            )}
            {doc.hospitals && doc.hospitals.length > 0 && (
              <p className="text-gray-400 text-xs mt-2">{doc.hospitals.join(', ')}</p>
            )}
            <button
              onClick={() => navigate(`/patient/book-appointment/${doc.id}`)}
              className="mt-4 w-full px-4 py-2 bg-blue-600 text-white rounded-lg hover:bg-blue-700 text-sm font-medium"
            >
              Book Appointment
            </button>
          </div>
        ))}
        {!loading && doctors.length === 0 && (
          <div className="col-span-full text-center py-12 text-gray-500">
            No doctors found. Try different search criteria.
          </div>
        )}
      </div>
    </div>
  );
}

