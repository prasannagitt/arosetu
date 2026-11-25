import { useState } from 'react';
import { toast } from 'react-toastify';

export default function MessageForm() {
  const [form, setForm] = useState({ firstName: '', lastName: '', email: '', phone: '', message: '' });
  const [sending, setSending] = useState(false);

  const handleChange = (e) => setForm({ ...form, [e.target.name]: e.target.value });

  const handleSubmit = (e) => {
    e.preventDefault();
    setSending(true);
    // Simulate send (no backend message endpoint yet)
    setTimeout(() => {
      toast.success('Thank you for your message! We will get back to you soon.');
      setForm({ firstName: '', lastName: '', email: '', phone: '', message: '' });
      setSending(false);
    }, 800);
  };

  return (
    <div className="message-form">
      <h2>Send Us A Message</h2>
      <p className="form-subtitle">Have a question or need assistance? Reach out to us!</p>
      <form onSubmit={handleSubmit}>
        <div className="form-row">
          <input type="text" name="firstName" placeholder="First Name" value={form.firstName} onChange={handleChange} required />
          <input type="text" name="lastName" placeholder="Last Name" value={form.lastName} onChange={handleChange} required />
        </div>
        <div className="form-row">
          <input type="email" name="email" placeholder="Email" value={form.email} onChange={handleChange} required />
          <input type="tel" name="phone" placeholder="Phone Number" value={form.phone} onChange={handleChange} />
        </div>
        <textarea name="message" rows={5} placeholder="Your Message" value={form.message} onChange={handleChange} required />
        <div style={{ display: 'flex', justifyContent: 'center' }}>
          <button type="submit" className="btn-primary" disabled={sending}>
            {sending ? 'Sending...' : 'Send Message'}
          </button>
        </div>
      </form>
    </div>
  );
}
