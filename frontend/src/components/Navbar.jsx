import { useState } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';
import { toast } from 'react-toastify';
import { GiHamburgerMenu } from 'react-icons/gi';
import { AiOutlineClose } from 'react-icons/ai';

const navLinks = {
  PATIENT: [
    { to: '/patient/dashboard', label: 'Dashboard' },
    { to: '/patient/doctors', label: 'Find Doctors' },
    { to: '/patient/appointments', label: 'Appointments' },
    { to: '/patient/consultations', label: 'Consultations' },
    { to: '/patient/prescriptions', label: 'Prescriptions' },
    { to: '/patient/lab-reports', label: 'Lab Reports' },
  ],
  DOCTOR: [
    { to: '/doctor/dashboard', label: 'Dashboard' },
    { to: '/doctor/appointments', label: 'Appointments' },
    { to: '/doctor/consultations', label: 'Consultations' },
    { to: '/doctor/prescriptions', label: 'Prescriptions' },
    { to: '/doctor/availability', label: 'Availability' },
    { to: '/doctor/profile', label: 'Profile' },
  ],
  LAB_TECHNICIAN: [
    { to: '/lab/dashboard', label: 'Dashboard' },
  ],
  PHARMACIST: [
    { to: '/pharmacist/dashboard', label: 'Dashboard' },
  ],
  ADMIN: [
    { to: '/admin/dashboard', label: 'Dashboard' },
    { to: '/admin/users', label: 'Users' },
    { to: '/admin/doctors', label: 'Doctors' },
    { to: '/admin/hospitals', label: 'Hospitals' },
    { to: '/admin/laboratories', label: 'Laboratories' },
    { to: '/admin/pharmacies', label: 'Pharmacies' },
  ],
};

const roleBadgeColors = {
  PATIENT: '#10b981',
  DOCTOR: '#3b82f6',
  LAB_TECHNICIAN: '#8b5cf6',
  PHARMACIST: '#f97316',
  ADMIN: '#ef4444',
};

export default function Navbar() {
  const { user, logout } = useAuth();
  const navigate = useNavigate();
  const location = useLocation();
  const [mobileOpen, setMobileOpen] = useState(false);

  const links = navLinks[user?.role] || [];
  const badgeColor = roleBadgeColors[user?.role] || '#6b7280';

  const handleLogout = () => {
    logout();
    toast.info('You have been logged out.');
    navigate('/login');
  };

  return (
    <nav style={{
      background: '#0f172a',
      color: '#fff',
      padding: '0 30px',
      position: 'sticky',
      top: 0,
      zIndex: 50,
      boxShadow: '0 2px 10px rgba(0,0,0,0.3)'
    }}>
      <div style={{
        maxWidth: '1280px', margin: '0 auto',
        display: 'flex', justifyContent: 'space-between', alignItems: 'center',
        height: '64px'
      }}>
        {/* Brand */}
        <Link to="/" style={{ textDecoration: 'none', color: '#fff', fontSize: '22px', fontWeight: 900, letterSpacing: '2px' }}>
          AroSetu
        </Link>

        {/* Desktop nav */}
        <div className="hidden md:flex" style={{ display: 'flex', alignItems: 'center', gap: '4px' }}>
          {links.map((link) => (
            <Link
              key={link.to}
              to={link.to}
              style={{
                padding: '8px 14px', borderRadius: '8px', fontSize: '14px',
                fontWeight: 600, textDecoration: 'none', transition: 'all 0.2s',
                color: location.pathname === link.to ? '#fff' : '#94a3b8',
                background: location.pathname === link.to ? '#1e40af' : 'transparent',
              }}
            >
              {link.label}
            </Link>
          ))}
        </div>

        {/* User info + logout */}
        <div className="hidden md:flex" style={{ display: 'flex', alignItems: 'center', gap: '16px' }}>
          <div style={{ display: 'flex', alignItems: 'center', gap: '8px' }}>
            <span style={{ fontSize: '14px', fontWeight: 600, color: '#e2e8f0' }}>{user?.name}</span>
            <span style={{
              padding: '3px 10px', borderRadius: '20px', fontSize: '11px',
              fontWeight: 700, color: '#fff', background: badgeColor, textTransform: 'uppercase',
              letterSpacing: '0.5px'
            }}>
              {user?.role?.replace(/_/g, ' ')}
            </span>
          </div>
          <button
            onClick={handleLogout}
            style={{
              padding: '8px 18px', borderRadius: '8px', fontSize: '13px',
              fontWeight: 600, background: '#dc2626', color: '#fff', border: 'none',
              cursor: 'pointer', transition: 'background 0.2s'
            }}
          >
            Logout
          </button>
        </div>

        {/* Mobile hamburger */}
        <div className="md:hidden" style={{ cursor: 'pointer', fontSize: '24px' }}
          onClick={() => setMobileOpen(!mobileOpen)}>
          {mobileOpen ? <AiOutlineClose /> : <GiHamburgerMenu />}
        </div>
      </div>

      {/* Mobile menu */}
      {mobileOpen && (
        <div style={{
          background: '#1e293b', padding: '16px', borderTop: '1px solid #334155'
        }}>
          {links.map((link) => (
            <Link
              key={link.to}
              to={link.to}
              onClick={() => setMobileOpen(false)}
              style={{
                display: 'block', padding: '10px 16px', borderRadius: '8px',
                fontSize: '14px', fontWeight: 600, textDecoration: 'none',
                color: location.pathname === link.to ? '#fff' : '#94a3b8',
                background: location.pathname === link.to ? '#1e40af' : 'transparent',
                marginBottom: '4px'
              }}
            >
              {link.label}
            </Link>
          ))}
          <div style={{ marginTop: '12px', paddingTop: '12px', borderTop: '1px solid #475569' }}>
            <div style={{ display: 'flex', alignItems: 'center', gap: '8px', padding: '8px 16px' }}>
              <span style={{ fontSize: '14px', color: '#e2e8f0' }}>{user?.name}</span>
              <span style={{
                padding: '2px 8px', borderRadius: '12px', fontSize: '10px',
                fontWeight: 700, color: '#fff', background: badgeColor
              }}>
                {user?.role?.replace(/_/g, ' ')}
              </span>
            </div>
            <button
              onClick={handleLogout}
              style={{
                width: '100%', padding: '10px', borderRadius: '8px', fontSize: '14px',
                fontWeight: 600, background: '#dc2626', color: '#fff', border: 'none',
                cursor: 'pointer', marginTop: '8px'
              }}
            >
              Logout
            </button>
          </div>
        </div>
      )}
    </nav>
  );
}
