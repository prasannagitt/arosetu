import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom';
import { AuthProvider, useAuth } from './context/AuthContext';
import ProtectedRoute from './components/ProtectedRoute';
import Layout from './components/Layout';
import { ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

// Public pages
import Home from './pages/Home';
import About from './pages/About';

// Auth pages
import Login from './pages/auth/Login';
import Register from './pages/auth/Register';

// Patient pages
import PatientDashboard from './pages/patient/Dashboard';
import DoctorSearch from './pages/patient/DoctorSearch';
import BookAppointment from './pages/patient/BookAppointment';
import MyAppointments from './pages/patient/MyAppointments';
import MyConsultations from './pages/patient/MyConsultations';
import MyPrescriptions from './pages/patient/MyPrescriptions';
import MyLabReports from './pages/patient/MyLabReports';

// Doctor pages
import DoctorDashboard from './pages/doctor/Dashboard';
import DoctorProfile from './pages/doctor/Profile';
import DoctorAppointments from './pages/doctor/Appointments';
import DoctorConsultations from './pages/doctor/Consultations';
import DoctorPrescriptions from './pages/doctor/Prescriptions';
import DoctorAvailability from './pages/doctor/Availability';

// Lab Technician pages
import LabDashboard from './pages/lab/Dashboard';

// Pharmacist pages
import PharmacistDashboard from './pages/pharmacist/Dashboard';

// Admin pages
import AdminDashboard from './pages/admin/Dashboard';
import AdminUsers from './pages/admin/Users';
import AdminDoctors from './pages/admin/Doctors';
import AdminHospitals from './pages/admin/Hospitals';
import AdminLaboratories from './pages/admin/Laboratories';
import AdminPharmacies from './pages/admin/Pharmacies';

function HomeRedirect() {
  const { user, isAuthenticated } = useAuth();
  if (!isAuthenticated) return <Navigate to="/" replace />;
  const routes = {
    PATIENT: '/patient/dashboard',
    DOCTOR: '/doctor/dashboard',
    LAB_TECHNICIAN: '/lab/dashboard',
    PHARMACIST: '/pharmacist/dashboard',
    ADMIN: '/admin/dashboard',
  };
  return <Navigate to={routes[user?.role] || '/'} replace />;
}

export default function App() {
  return (
    <BrowserRouter>
      <AuthProvider>
        <ToastContainer
          position="top-right"
          autoClose={3000}
          hideProgressBar={false}
          newestOnTop
          closeOnClick
          pauseOnHover
          theme="colored"
        />
        <Routes>
          {/* Public routes */}
          <Route path="/" element={<Home />} />
          <Route path="/about" element={<About />} />
          <Route path="/login" element={<Login />} />
          <Route path="/register" element={<Register />} />
          <Route path="/dashboard" element={<HomeRedirect />} />

          {/* Patient routes */}
          <Route element={<ProtectedRoute allowedRoles={['PATIENT']} />}>
            <Route element={<Layout />}>
              <Route path="/patient/dashboard" element={<PatientDashboard />} />
              <Route path="/patient/doctors" element={<DoctorSearch />} />
              <Route path="/patient/book-appointment/:doctorId" element={<BookAppointment />} />
              <Route path="/patient/appointments" element={<MyAppointments />} />
              <Route path="/patient/consultations" element={<MyConsultations />} />
              <Route path="/patient/prescriptions" element={<MyPrescriptions />} />
              <Route path="/patient/lab-reports" element={<MyLabReports />} />
            </Route>
          </Route>

          {/* Doctor routes */}
          <Route element={<ProtectedRoute allowedRoles={['DOCTOR']} />}>
            <Route element={<Layout />}>
              <Route path="/doctor/dashboard" element={<DoctorDashboard />} />
              <Route path="/doctor/profile" element={<DoctorProfile />} />
              <Route path="/doctor/appointments" element={<DoctorAppointments />} />
              <Route path="/doctor/consultations" element={<DoctorConsultations />} />
              <Route path="/doctor/prescriptions" element={<DoctorPrescriptions />} />
              <Route path="/doctor/availability" element={<DoctorAvailability />} />
            </Route>
          </Route>

          {/* Lab Technician routes */}
          <Route element={<ProtectedRoute allowedRoles={['LAB_TECHNICIAN']} />}>
            <Route element={<Layout />}>
              <Route path="/lab/dashboard" element={<LabDashboard />} />
            </Route>
          </Route>

          {/* Pharmacist routes */}
          <Route element={<ProtectedRoute allowedRoles={['PHARMACIST']} />}>
            <Route element={<Layout />}>
              <Route path="/pharmacist/dashboard" element={<PharmacistDashboard />} />
            </Route>
          </Route>

          {/* Admin routes */}
          <Route element={<ProtectedRoute allowedRoles={['ADMIN']} />}>
            <Route element={<Layout />}>
              <Route path="/admin/dashboard" element={<AdminDashboard />} />
              <Route path="/admin/users" element={<AdminUsers />} />
              <Route path="/admin/doctors" element={<AdminDoctors />} />
              <Route path="/admin/hospitals" element={<AdminHospitals />} />
              <Route path="/admin/laboratories" element={<AdminLaboratories />} />
              <Route path="/admin/pharmacies" element={<AdminPharmacies />} />
            </Route>
          </Route>

          {/* Catch all */}
          <Route path="*" element={<Navigate to="/" replace />} />
        </Routes>
      </AuthProvider>
    </BrowserRouter>
  );
}
