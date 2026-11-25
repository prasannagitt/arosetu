import PublicNavbar from '../components/PublicNavbar';
import Hero from '../components/Hero';
import Biography from '../components/Biography';
import Departments from '../components/Departments';
import Stats from '../components/Stats';
import MessageForm from '../components/MessageForm';
import Footer from '../components/Footer';

export default function Home() {
  return (
    <>
      <PublicNavbar />
      <Hero />
      <Stats />
      <Biography />
      <Departments />
      <MessageForm />
      <Footer />
    </>
  );
}
