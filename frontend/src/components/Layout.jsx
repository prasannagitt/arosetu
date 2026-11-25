import Navbar from './Navbar';
import { Outlet } from 'react-router-dom';

export default function Layout() {
  return (
    <div style={{ display: 'flex', flexDirection: 'column', minHeight: '100vh', background: '#f1f5f9' }}>
      <Navbar />
      <main style={{ flex: 1, maxWidth: '1280px', width: '100%', margin: '0 auto', padding: '24px 24px 48px' }}>
        <Outlet />
      </main>
      <footer style={{
        background: '#0f172a', color: '#64748b', textAlign: 'center',
        padding: '20px', fontSize: '13px'
      }}>
        &copy; {new Date().getFullYear()} AroSetu Healthcare &mdash; Built by Prasanna Ram &amp; Sudarshan K S
      </footer>
    </div>
  );
}
