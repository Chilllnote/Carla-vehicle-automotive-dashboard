import React from 'react';
import { Navigate, Outlet } from 'react-router-dom';

const ProtectedRoute = ({service}: {service: any}) => {
    // Check if user exists in localStorage
    const user = service.getStoredUser();

    if (!user) {
        // If no user, redirect to login
        return <Navigate to="/login" replace />;
    }

    // If user exists, render the component (children)
    //console.log('User authenticated:', JSON.parse(user));
    return <Outlet />;
};

export default ProtectedRoute;