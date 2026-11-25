import { FaHeartPulse } from 'react-icons/fa6';

export default function Hero() {
  return (
    <div className="hero">
      <div className="banner">
        <h1>
          Welcome to <span>AroSetu</span> — Your Health, Our Priority
        </h1>
        <p>
          AroSetu is a state-of-the-art healthcare coordination platform 
          committed to providing comprehensive services with compassion and 
          expertise. Our team of skilled professionals delivers personalized 
          care tailored to each patient's unique needs. Book appointments, 
          consult doctors online, manage prescriptions, and track lab reports 
          — all in one seamless platform.
        </p>
        <div className="flex gap-4">
          <a href="/register" className="btn-primary">Get Started</a>
          <a href="/about" className="btn-primary" style={{ background: 'transparent', color: '#1a56db', border: '2px solid #1a56db' }}>
            Learn More
          </a>
        </div>
      </div>
      <div className="banner">
        <div className="hero-visual">
          <FaHeartPulse className="hero-icon" />
        </div>
      </div>
    </div>
  );
}
