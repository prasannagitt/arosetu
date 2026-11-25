import { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { useAuth } from '../../context/AuthContext';
import { toast } from 'react-toastify';

export default function Login() {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');
  const [loading, setLoading] = useState(false);
  const { login } = useAuth();
  const navigate = useNavigate();

  const roleRedirects = {
    PATIENT: '/patient/dashboard',
    DOCTOR: '/doctor/dashboard',
    LAB_TECHNICIAN: '/lab/dashboard',
    PHARMACIST: '/pharmacist/dashboard',
    ADMIN: '/admin/dashboard',
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError('');
    setLoading(true);
    try {
      const data = await login(email, password);
      toast.success(`Welcome back, ${data.name}!`);
      navigate(roleRedirects[data.role] || '/');
    } catch (err) {
      const msg = err.response?.data?.message || err.response?.data?.error || 'Invalid email or password';
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
        <h2 className="auth-title">Welcome Back</h2>
        <p style={{ textAlign: 'center', color: '#64748b', fontSize: '14px', marginBottom: '24px', marginTop: '-16px' }}>
          Please login to manage your appointments and stay connected with your healthcare provider.
        </p>

        {error && <div className="auth-error">{error}</div>}

        <form onSubmit={handleSubmit}>
          <div className="form-group">
            <label htmlFor="email">Email</label>
            <input id="email" type="email" required value={email} onChange={(e) => setEmail(e.target.value)}
              placeholder="you@example.com" />
          </div>
          <div className="form-group">
            <label htmlFor="password">Password</label>
            <input id="password" type="password" required value={password} onChange={(e) => setPassword(e.target.value)}
              placeholder="Enter your password" />
          </div>
          <button type="submit" className="auth-submit" disabled={loading}>
            {loading ? 'Signing in...' : 'Sign In'}
          </button>
        </form>
        <p className="auth-link">
          Not registered? <Link to="/register">Create an account</Link>
        </p>
      </div>
    </div>
  );
}
