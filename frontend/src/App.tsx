import { Navigate, Route, Routes } from 'react-router-dom';
import LoginPage from './pages/LoginPage';
import RegisterPage from './pages/RegisterPage';
import DashboardPage from './pages/DashboardPage';
import { AuthProvider, useAuth } from './context/AuthContext';

const ProtectedRoute = ({ children }: { children: JSX.Element }) => {
    const auth = useAuth();
    return auth.authenticated ? children : <Navigate to="/" replace />;
};

const AppRoutes = () => (
    <Routes>
        <Route path="/" element={<LoginPage />} />
        <Route path="/login" element={<LoginPage />} />
        <Route path="/register" element={<RegisterPage />} />
        <Route path="/dashboard" element={<ProtectedRoute><DashboardPage /></ProtectedRoute>} />
        <Route path="*" element={<Navigate to="/" replace />} />
    </Routes>
);

const App = () => (
    <AuthProvider>
        <AppRoutes />
    </AuthProvider>
);

export default App;
