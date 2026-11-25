import { useState } from 'react';
import { Link } from 'react-router-dom';
import { GiHamburgerMenu } from 'react-icons/gi';
import { AiOutlineClose } from 'react-icons/ai';

export default function PublicNavbar() {
  const [open, setOpen] = useState(false);

  return (
    <nav className="public-nav">
      <Link to="/" className="nav-brand">AroSetu</Link>
      <div className={`nav-links${open ? ' open' : ''}`}>
        <Link to="/" onClick={() => setOpen(false)}>Home</Link>
        <Link to="/about" onClick={() => setOpen(false)}>About Us</Link>
        <Link to="/login" className="nav-btn" onClick={() => setOpen(false)}>Login</Link>
        <Link to="/register" className="nav-btn" onClick={() => setOpen(false)}>Register</Link>
      </div>
      <div className="hamburger" onClick={() => setOpen(!open)}>
        {open ? <AiOutlineClose /> : <GiHamburgerMenu />}
      </div>
    </nav>
  );
}
