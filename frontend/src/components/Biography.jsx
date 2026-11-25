import { FaHospital } from 'react-icons/fa6';

export default function Biography() {
  return (
    <div className="biography">
      <div className="banner">
        <div className="about-visual">
          <FaHospital className="about-icon" />
        </div>
      </div>
      <div className="banner">
        <p>About AroSetu</p>
        <h3>Who We Are</h3>
        <p>
          AroSetu Healthcare is a premier digital health platform dedicated to
          bridging the gap between patients and healthcare providers. Our
          expert team is committed to delivering a seamless, personalized
          healthcare experience that ensures every patient receives the
          highest standard of care.
        </p>
        <p>
          Our platform is built with cutting-edge technology, connecting 
          patients with top doctors, enabling online consultations, managing
          lab tests, and streamlining pharmacy services — all from the 
          comfort of your home.
        </p>
        <p>
          We believe in a patient-centered approach, where each individual's
          needs are addressed with attention, expertise, and compassion.
          AroSetu makes healthcare accessible, efficient, and transparent.
        </p>
      </div>
    </div>
  );
}
