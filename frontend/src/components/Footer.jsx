import { Link } from 'react-router-dom';
import { FaPhone, FaGithub, FaLinkedin } from 'react-icons/fa6';
import { MdEmail, MdLocationOn } from 'react-icons/md';

export default function Footer() {
  return (
    <footer className="site-footer">
      <div className="footer-content">
        <div className="footer-brand">
          <div className="brand-name">AroSetu</div>
          <p className="brand-tagline">Healthcare Coordination Platform</p>
          <div className="dev-section">
            <p>Developed by</p>
            <p className="dev-name">Prasanna Ram</p>
            <p className="dev-name">Sudarshan K S</p>
          </div>
          <div className="social-icons">
            <a href="https://github.com" target="_blank" rel="noopener noreferrer"><FaGithub /></a>
            <a href="https://linkedin.com" target="_blank" rel="noopener noreferrer"><FaLinkedin /></a>
          </div>
        </div>

        <div>
          <h4>Quick Links</h4>
          <ul>
            <li><Link to="/">Home</Link></li>
            <li><Link to="/about">About Us</Link></li>
            <li><Link to="/login">Login</Link></li>
            <li><Link to="/register">Register</Link></li>
          </ul>
        </div>

        <div>
          <h4>Our Services</h4>
          <ul>
            <li>Appointment Booking</li>
            <li>Online Consultations</li>
            <li>Lab Test Management</li>
            <li>Digital Prescriptions</li>
            <li>Pharmacy Services</li>
          </ul>
        </div>

        <div>
          <h4>Contact Us</h4>
          <div className="contact-item">
            <FaPhone />
            <span>+91 98765 43210</span>
          </div>
          <div className="contact-item">
            <MdEmail />
            <span>contact@arosetu.com</span>
          </div>
          <div className="contact-item">
            <MdLocationOn />
            <span>Bangalore, Karnataka, India</span>
          </div>
        </div>
      </div>
      <div className="copyright">
        <p>&copy; {new Date().getFullYear()} AroSetu Healthcare. All rights reserved.</p>
        <p style={{ marginTop: '4px' }}>Built by Prasanna Ram &amp; Sudarshan K S</p>
      </div>
    </footer>
  );
}
