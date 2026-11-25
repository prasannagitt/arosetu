import PublicNavbar from '../components/PublicNavbar';
import Footer from '../components/Footer';
import { FaHospital, FaUserDoctor, FaFlask, FaPills, FaCalendarCheck, FaShieldHalved } from 'react-icons/fa6';

const features = [
  { icon: FaCalendarCheck, title: 'Smart Appointments', desc: 'Book appointments with top doctors based on real-time availability slots.' },
  { icon: FaUserDoctor, title: 'Online Consultations', desc: 'Get expert medical consultations from the comfort of your home.' },
  { icon: FaFlask, title: 'Lab Management', desc: 'Request, track, and receive lab test results digitally.' },
  { icon: FaPills, title: 'Digital Prescriptions', desc: 'Receive and manage prescriptions digitally, with pharmacy integration.' },
  { icon: FaHospital, title: 'Multi-Hospital Network', desc: 'Access a network of hospitals and healthcare providers.' },
  { icon: FaShieldHalved, title: 'Secure & Private', desc: 'Your health data is encrypted and secure with JWT authentication.' },
];

export default function About() {
  return (
    <>
      <PublicNavbar />
      <div style={{ paddingTop: '100px' }}>
        {/* Hero */}
        <div style={{ padding: '60px 100px', background: 'linear-gradient(135deg, #1e40af, #3b82f6)', color: '#fff', textAlign: 'center' }}>
          <h1 style={{ fontSize: '40px', fontWeight: 900, marginBottom: '16px' }}>About AroSetu</h1>
          <p style={{ fontSize: '18px', maxWidth: '700px', margin: '0 auto', opacity: 0.9, lineHeight: 1.8 }}>
            AroSetu is a comprehensive healthcare coordination platform built to bridge the gap
            between patients, doctors, laboratories, and pharmacies — all in one seamless experience.
          </p>
        </div>

        {/* Features Grid */}
        <div style={{ padding: '60px 100px' }}>
          <h2 style={{ textAlign: 'center', fontSize: '32px', fontWeight: 900, marginBottom: '40px', color: '#1e293b' }}>
            What We Offer
          </h2>
          <div style={{ display: 'grid', gridTemplateColumns: 'repeat(auto-fit, minmax(300px, 1fr))', gap: '30px' }}>
            {features.map((f, i) => {
              const Icon = f.icon;
              return (
                <div key={i} style={{
                  background: '#fff', borderRadius: '16px', padding: '30px',
                  boxShadow: '0 2px 20px rgba(0,0,0,0.06)', transition: 'transform 0.3s',
                  border: '1px solid #e2e8f0'
                }}>
                  <Icon style={{ fontSize: '36px', color: '#1a56db', marginBottom: '16px' }} />
                  <h3 style={{ fontSize: '20px', fontWeight: 700, color: '#1e293b', marginBottom: '8px' }}>{f.title}</h3>
                  <p style={{ color: '#64748b', fontSize: '15px', lineHeight: 1.7 }}>{f.desc}</p>
                </div>
              );
            })}
          </div>
        </div>

        {/* Team */}
        <div style={{ padding: '60px 100px', background: '#f8fafc', textAlign: 'center' }}>
          <h2 style={{ fontSize: '32px', fontWeight: 900, marginBottom: '16px', color: '#1e293b' }}>Our Team</h2>
          <p style={{ color: '#64748b', marginBottom: '40px', fontSize: '16px' }}>
            Built with passion by a dedicated team of developers.
          </p>
          <div style={{ display: 'flex', justifyContent: 'center', gap: '60px', flexWrap: 'wrap' }}>
            {[
              { name: 'Prasanna Ram', role: 'Full Stack Developer' },
              { name: 'Sudarshan K S', role: 'Full Stack Developer' },
            ].map((dev, i) => (
              <div key={i} style={{
                background: '#fff', borderRadius: '20px', padding: '30px 40px',
                boxShadow: '0 4px 20px rgba(0,0,0,0.06)', minWidth: '250px'
              }}>
                <div style={{
                  width: '80px', height: '80px', borderRadius: '50%',
                  background: 'linear-gradient(135deg, #1a56db, #3b82f6)',
                  display: 'flex', alignItems: 'center', justifyContent: 'center',
                  margin: '0 auto 16px', color: '#fff', fontSize: '32px', fontWeight: 900
                }}>
                  {dev.name[0]}
                </div>
                <h3 style={{ fontSize: '20px', fontWeight: 700, color: '#1e293b' }}>{dev.name}</h3>
                <p style={{ color: '#64748b', fontSize: '14px' }}>{dev.role}</p>
              </div>
            ))}
          </div>
        </div>

        {/* Tech Stack */}
        <div style={{ padding: '60px 100px', textAlign: 'center' }}>
          <h2 style={{ fontSize: '32px', fontWeight: 900, marginBottom: '30px', color: '#1e293b' }}>Technology Stack</h2>
          <div style={{ display: 'flex', justifyContent: 'center', gap: '20px', flexWrap: 'wrap' }}>
            {['Spring Boot 3', 'React 19', 'Tailwind CSS', 'Java 17', 'H2 / PostgreSQL', 'JWT Auth', 'Vite', 'REST API'].map((tech, i) => (
              <span key={i} style={{
                background: '#f0f4ff', color: '#1a56db', padding: '10px 24px',
                borderRadius: '20px', fontWeight: 600, fontSize: '14px',
                border: '1px solid #c7d2fe'
              }}>
                {tech}
              </span>
            ))}
          </div>
        </div>
      </div>
      <Footer />
    </>
  );
}
