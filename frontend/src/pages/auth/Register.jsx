import { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { useAuth } from '../../context/AuthContext';
import { toast } from 'react-toastify';

export default function Register() {
  const [form, setForm] = useState({ name: '', email: '', password: '', phone: '', role: 'PATIENT' });
  const [error, setError] = useState('');
  const [loading, setLoading] = useState(false);
  const { register } = useAuth();
  const navigate = useNavigate();

  const roleRedirects = {
    PATIENT: '/patient/dashboard',
    DOCTOR: '/doctor/dashboard',
    LAB_TECHNICIAN: '/lab/dashboard',
    PHARMACIST: '/pharmacist/dashboard',
    ADMIN: '/admin/dashboard',
  };

  const handleChange = (e) => setForm({ ...form, [e.target.name]: e.target.value });

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError('');
    setLoading(true);
    try {
      const data = await register(form);
      toast.success('Account created successfully! Welcome to AroSetu.');
      navigate(roleRedirects[data.role] || '/');
    } catch (err) {
      const msg = err.response?.data?.message || err.response?.data?.error || 'Registration failed';
      setError(msg);
      toast.error(msg);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="auth-page">
      <div className="auth-card">
        <div className="auth-brand">AroSetu</div>
        <p className="auth-subtitle">Healthcare Coordination Platform</p>
        <h2 className="auth-title">Create your account</h2>

        {error && <div className="auth-error">{error}</div>}

        <form onSubmit={handleSubmit}>
          <div className="form-group">
            <label htmlFor="name">Full Name</label>
            <input id="name" name="name" type="text" required value={form.name} onChange={handleChange}
              placeholder="Enter your full name" />
          </div>
          <div className="form-group">
            <label htmlFor="email">Email</label>
            <input id="email" name="email" type="email" required value={form.email} onChange={handleChange}
              placeholder="you@example.com" />
          </div>
          <div className="form-group">
            <label htmlFor="password">Password</label>
            <input id="password" name="password" type="password" required minLength={8} value={form.password} onChange={handleChange}
              placeholder="Min 8 characters" />
          </div>
          <div className="form-group">
            <label htmlFor="phone">Phone (optional)</label>
            <input id="phone" name="phone" type="tel" value={form.phone} onChange={handleChange}
              placeholder="+91 9876543210" />
          </div>
          <div className="form-group">
            <label htmlFor="role">Register as</label>
            <select id="role" name="role" value={form.role} onChange={handleChange}>
              <option value="PATIENT">Patient</option>
              <option value="DOCTOR">Doctor</option>
              <option value="LAB_TECHNICIAN">Lab Technician</option>
              <option value="PHARMACIST">Pharmacist</option>
            </select>
          </div>
          <button type="submit" className="auth-submit" disabled={loading}>
            {loading ? 'Creating Account...' : 'Create Account'}
          </button>
        </form>
        <p className="auth-link">
          Already have an account? <Link to="/login">Sign in</Link>
        </p>
      </div>
    </div>
  );
}
