import { FaHeartPulse, FaBone, FaBrain, FaLungs, FaEye, FaBaby, FaStethoscope, FaUserDoctor, FaTooth } from 'react-icons/fa6';

const departments = [
  { name: 'Cardiology', Icon: FaHeartPulse, color: '#ef4444' },
  { name: 'Orthopedics', Icon: FaBone, color: '#f97316' },
  { name: 'Neurology', Icon: FaBrain, color: '#8b5cf6' },
  { name: 'Pulmonology', Icon: FaLungs, color: '#06b6d4' },
  { name: 'Ophthalmology', Icon: FaEye, color: '#10b981' },
  { name: 'Pediatrics', Icon: FaBaby, color: '#ec4899' },
  { name: 'General Medicine', Icon: FaStethoscope, color: '#3b82f6' },
  { name: 'Dermatology', Icon: FaUserDoctor, color: '#a855f7' },
  { name: 'Dentistry', Icon: FaTooth, color: '#14b8a6' },
];

export default function Departments() {
  return (
    <div style={{ padding: '60px 100px' }}>
      <h2 style={{ color: '#1e293b', marginBottom: '30px', fontSize: '32px', fontWeight: 900, letterSpacing: '2px' }}>
        Our Departments
      </h2>
      <div style={{
        display: 'grid',
        gridTemplateColumns: 'repeat(auto-fill, minmax(250px, 1fr))',
        gap: '20px',
      }}>
        {departments.map((dept, i) => {
          const Icon = dept.Icon;
          return (
            <div
              key={i}
              style={{
                background: `linear-gradient(135deg, ${dept.color}15, ${dept.color}30)`,
                border: `1px solid ${dept.color}20`,
                borderRadius: '16px',
                padding: '30px 20px',
                display: 'flex',
                flexDirection: 'column',
                alignItems: 'center',
                gap: '16px',
                transition: 'all 0.3s',
                cursor: 'pointer',
              }}
              onMouseOver={(e) => {
                e.currentTarget.style.transform = 'translateY(-4px)';
                e.currentTarget.style.boxShadow = `0 8px 25px ${dept.color}30`;
              }}
              onMouseOut={(e) => {
                e.currentTarget.style.transform = 'translateY(0)';
                e.currentTarget.style.boxShadow = 'none';
              }}
            >
              <Icon style={{ fontSize: '48px', color: dept.color }} />
              <div style={{
                background: '#fff',
                padding: '8px 20px',
                borderRadius: '20px',
                fontSize: '14px',
                textTransform: 'uppercase',
                fontWeight: 700,
                color: '#1e293b',
                letterSpacing: '1px',
              }}>
                {dept.name}
              </div>
            </div>
          );
        })}
      </div>
    </div>
  );
}
