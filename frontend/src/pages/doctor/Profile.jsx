import { useState, useEffect } from 'react';
import { getMyDoctorProfile, updateMyDoctorProfile } from '../../api/doctorApi';

export default function DoctorProfile() {
  const [form, setForm] = useState({
    specialization: '', qualification: '', experienceYears: '', licenseNumber: '', consultationFee: '', about: ''
  });
  const [loading, setLoading] = useState(true);
  const [saving, setSaving] = useState(false);
  const [message, setMessage] = useState('');

  useEffect(() => {
    getMyDoctorProfile().then(r => {
      const d = r.data;
      setForm({
        specialization: d.specialization || '', qualification: d.qualification || '',
        experienceYears: d.experienceYears || '', licenseNumber: d.licenseNumber || '',
        consultationFee: d.consultationFee || '', about: d.about || ''
      });
    }).catch(console.error).finally(() => setLoading(false));
  }, []);

  const handleChange = (e) => setForm({ ...form, [e.target.name]: e.target.value });

  const handleSubmit = async (e) => {
    e.preventDefault();
    setSaving(true);
    setMessage('');
    try {
      await updateMyDoctorProfile({
        ...form,
        experienceYears: parseInt(form.experienceYears) || 0,
        consultationFee: parseFloat(form.consultationFee) || 0,
      });
      setMessage('Profile updated successfully!');
    } catch (err) {
      setMessage('Failed to update profile');
    } finally {
      setSaving(false);
    }
  };

  if (loading) return <div className="flex justify-center py-12"><div className="animate-spin rounded-full h-12 w-12 border-b-2 border-blue-600"></div></div>;

  return (
    <div className="max-w-2xl mx-auto space-y-6">
      <h1 className="text-2xl font-bold text-gray-900">My Profile</h1>
      <form onSubmit={handleSubmit} className="bg-white rounded-xl shadow-md p-6 space-y-4">
        {message && (
          <div className={`px-4 py-3 rounded-lg text-sm ${message.includes('success') ? 'bg-green-50 text-green-700' : 'bg-red-50 text-red-700'}`}>
            {message}
          </div>
        )}
        {[
          { name: 'specialization', label: 'Specialization', type: 'text', placeholder: 'e.g., Cardiology' },
          { name: 'qualification', label: 'Qualification', type: 'text', placeholder: 'e.g., MBBS, MD' },
          { name: 'experienceYears', label: 'Experience (years)', type: 'number', placeholder: '5' },
          { name: 'licenseNumber', label: 'License Number', type: 'text', placeholder: 'MCI-12345' },
          { name: 'consultationFee', label: 'Consultation Fee (₹)', type: 'number', placeholder: '500' },
        ].map((field) => (
          <div key={field.name}>
            <label className="block text-sm font-medium text-gray-700 mb-1">{field.label}</label>
            <input name={field.name} type={field.type} value={form[field.name]} onChange={handleChange}
              placeholder={field.placeholder}
              className="w-full px-3 py-2 border border-gray-300 rounded-lg focus:ring-blue-500 focus:border-blue-500" />
          </div>
        ))}
        <div>
          <label className="block text-sm font-medium text-gray-700 mb-1">About</label>
          <textarea name="about" value={form.about} onChange={handleChange} rows={4}
            className="w-full px-3 py-2 border border-gray-300 rounded-lg focus:ring-blue-500 focus:border-blue-500"
            placeholder="Tell patients about yourself..." />
        </div>
        <button type="submit" disabled={saving}
          className="w-full py-2.5 px-4 bg-blue-600 text-white rounded-lg hover:bg-blue-700 disabled:opacity-50 font-medium">
          {saving ? 'Saving...' : 'Update Profile'}
        </button>
      </form>
    </div>
  );
}

