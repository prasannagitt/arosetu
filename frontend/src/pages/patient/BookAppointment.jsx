import { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import { getDoctorById } from '../../api/doctorApi';
import { getActiveHospitals } from '../../api/hospitalApi';
import { getAvailabilityByDoctor, getAvailableSlots } from '../../api/availabilityApi';
import { createAppointment } from '../../api/appointmentApi';

export default function BookAppointment() {
  const { doctorId } = useParams();
  const navigate = useNavigate();
  const [doctor, setDoctor] = useState(null);
  const [hospitals, setHospitals] = useState([]);
  const [availability, setAvailability] = useState([]);
  const [selectedHospital, setSelectedHospital] = useState('');
  const [selectedDate, setSelectedDate] = useState('');
  const [slots, setSlots] = useState([]);
  const [selectedSlot, setSelectedSlot] = useState('');
  const [reason, setReason] = useState('');
  const [loading, setLoading] = useState(true);
  const [submitting, setSubmitting] = useState(false);
  const [error, setError] = useState('');
  const [slotsLoading, setSlotsLoading] = useState(false);

  useEffect(() => {
    // Guard against invalid/undefined doctorId
    if (!doctorId || doctorId === 'undefined' || doctorId === 'null') {
      setError('Invalid doctor selected. Please go back and choose a doctor.');
      setLoading(false);
      return;
    }

    Promise.all([
      getDoctorById(doctorId).catch(() => ({ data: null })),
      getActiveHospitals().catch(() => ({ data: [] })),
      getAvailabilityByDoctor(doctorId).catch(() => ({ data: [] })),
    ]).then(([d, h, a]) => {
      setDoctor(d.data);
      setHospitals(h.data || []);
      setAvailability(a.data || []);
      if (!d.data) {
        setError('Doctor not found. Please go back and choose a valid doctor.');
      }
      setLoading(false);
    });
  }, [doctorId]);

  useEffect(() => {
    if (selectedHospital && selectedDate) {
      setSlotsLoading(true);
      getAvailableSlots(doctorId, selectedHospital, selectedDate)
        .then((res) => { setSlots(res.data || []); setSlotsLoading(false); })
        .catch(() => { setSlots([]); setSlotsLoading(false); });
    } else {
      setSlots([]);
    }
    setSelectedSlot('');
  }, [selectedHospital, selectedDate, doctorId]);

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError('');
    setSubmitting(true);
    try {
      await createAppointment({
        doctorId: parseInt(doctorId),
        hospitalId: parseInt(selectedHospital),
        appointmentDate: selectedDate,
        startTime: selectedSlot,
        reason,
      });
      navigate('/patient/appointments');
    } catch (err) {
      setError(err.response?.data?.message || 'Failed to book appointment');
    } finally {
      setSubmitting(false);
    }
  };

  if (loading) return <div className="flex justify-center py-12"><div className="animate-spin rounded-full h-12 w-12 border-b-2 border-blue-600"></div></div>;

  // Hospitals where this doctor has availability
  const doctorHospitalIds = availability.map(a => a.hospitalId);
  const filteredHospitals = hospitals.filter(h => doctorHospitalIds.includes(h.id));
  // Fallback: if no availability data, show all hospitals
  const displayHospitals = filteredHospitals.length > 0 ? filteredHospitals : hospitals;
  const today = new Date().toISOString().split('T')[0];

  return (
    <div className="max-w-2xl mx-auto space-y-6">
      <div className="flex items-center gap-4">
        <button onClick={() => navigate('/patient/doctors')}
          className="text-blue-600 hover:text-blue-800 text-sm font-medium">
          ← Back to Doctors
        </button>
        <h1 className="text-2xl font-bold text-gray-900">Book Appointment</h1>
      </div>

      {error && !doctor && (
        <div className="bg-red-50 border border-red-200 text-red-700 px-6 py-4 rounded-xl text-center">
          <p className="font-medium">{error}</p>
          <button onClick={() => navigate('/patient/doctors')}
            className="mt-3 px-4 py-2 bg-blue-600 text-white rounded-lg hover:bg-blue-700 text-sm font-medium">
            Find Doctors
          </button>
        </div>
      )}

      {doctor && (
        <>
          <div className="bg-white rounded-xl shadow-md p-6">
            <h2 className="text-xl font-semibold">{doctor.name}</h2>
            <p className="text-blue-600 text-sm">{doctor.specialization}</p>
            <p className="text-gray-500 text-sm">{doctor.qualification} • {doctor.experienceYears} yrs exp</p>
            {doctor.consultationFee && <p className="text-gray-700 text-sm mt-1">Fee: ₹{doctor.consultationFee}</p>}
          </div>

          <form onSubmit={handleSubmit} className="bg-white rounded-xl shadow-md p-6 space-y-4">
            {error && <div className="bg-red-50 border border-red-200 text-red-700 px-4 py-3 rounded-lg text-sm">{error}</div>}

            <div>
              <label className="block text-sm font-medium text-gray-700 mb-1">Hospital</label>
              <select value={selectedHospital} onChange={(e) => setSelectedHospital(e.target.value)} required
                className="w-full px-3 py-2 border border-gray-300 rounded-lg focus:ring-blue-500 focus:border-blue-500">
                <option value="">Select a hospital</option>
                {displayHospitals.map((h) => (
                  <option key={h.id} value={h.id}>{h.name} — {h.city}</option>
                ))}
              </select>
              {displayHospitals.length === 0 && (
                <p className="text-xs text-amber-600 mt-1">No hospitals available. Contact admin.</p>
              )}
            </div>

            <div>
              <label className="block text-sm font-medium text-gray-700 mb-1">Date</label>
              <input type="date" min={today} value={selectedDate} onChange={(e) => setSelectedDate(e.target.value)} required
                className="w-full px-3 py-2 border border-gray-300 rounded-lg focus:ring-blue-500 focus:border-blue-500" />
            </div>

            {selectedHospital && selectedDate && (
              <div>
                <label className="block text-sm font-medium text-gray-700 mb-2">Available Time Slots</label>
                {slotsLoading ? (
                  <p className="text-gray-500 text-sm">Loading slots...</p>
                ) : slots.length === 0 ? (
                  <p className="text-gray-500 text-sm">No available slots for this date. Try a different date or hospital.</p>
                ) : (
                  <div className="grid grid-cols-4 gap-2">
                    {slots.map((slot) => (
                      <button key={slot} type="button"
                        onClick={() => setSelectedSlot(slot)}
                        className={`px-3 py-2 text-sm rounded-lg border transition-colors ${
                          selectedSlot === slot
                            ? 'bg-blue-600 text-white border-blue-600'
                            : 'bg-white text-gray-700 border-gray-300 hover:border-blue-400'
                        }`}>
                        {slot}
                      </button>
                    ))}
                  </div>
                )}
              </div>
            )}

            <div>
              <label className="block text-sm font-medium text-gray-700 mb-1">Reason (optional)</label>
              <textarea value={reason} onChange={(e) => setReason(e.target.value)} rows={3}
                className="w-full px-3 py-2 border border-gray-300 rounded-lg focus:ring-blue-500 focus:border-blue-500"
                placeholder="Describe your symptoms or reason for visit..." />
            </div>

            <button type="submit" disabled={submitting || !selectedSlot}
              className="w-full py-2.5 px-4 bg-blue-600 text-white rounded-lg hover:bg-blue-700 disabled:opacity-50 font-medium">
              {submitting ? 'Booking...' : 'Book Appointment'}
            </button>
          </form>
        </>
      )}
    </div>
  );
}
