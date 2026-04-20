import React, { useState, useMemo } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from './assets/vite.svg'
import heroImg from './assets/hero.png'
import './App.css'
import { Route, Routes, Outlet } from 'react-router-dom'
import Landing from './components/landing'
import Metrics from './components/InternalPage/Metrics'
import { Navbar } from './components/Navbar'
import TestDashboard from './components/InternalPage/TestDashboard'
import Sidebar from './components/InternalPage/Sidebar'
import EmployeeProfile from './components/InternalPage/EmployeeProfile'
import Settings from './components/InternalPage/Settings'
import Algorithms from './components/InternalPage/Algorithms'
import Login from './components/InternalPage/Login'

import ProtectedRoute from './components/RouteGaurd/ProtectedRoute'
import applicationService from './components/service/ApplicationService'
// A wrapper for pages that NEED the sidebar
const DashboardLayout = ({ isSidebarOpen, toggleSidebar }: any) => {
  return (
    <div className="flex min-h-[calc(100vh-64px)] w-full">
      {/* Sidebar remains fixed, so it doesn't take up "flex" space */}

      {/* 1. Remove ml-64/ml-20. 
         2. Use padding-left (pl-64) instead. Padding pushes internal content 
            inward without breaking the width calculation of the box.
      */}
      <main
        className={`flex-1 w-full transition-all duration-300 ease-in-out ${isSidebarOpen ? 'pl-64' : 'pl-20'
          }`}
      >
        <div className="w-full h-full">
          <Outlet />
        </div>
      </main>

      <Sidebar
        isOpen={isSidebarOpen}
        onToggle={toggleSidebar}
        service={applicationService} />
    </div>

  );
};

function App() {
  const [isSidebarOpen, setIsSidebarOpen] = useState(true);

  return (
    <div className="min-h-screen bg-gray-50">
      <Navbar />

      <div className="pt-16"> {/* Offset for the fixed Navbar */}
        <Routes>


          {/* Public Routes */}
          <Route path="/login" element={<Login service={applicationService} />} />
          <Route path="/" element={<Landing service={applicationService} />} />

          {/* Protected Group */}
          <Route element={<ProtectedRoute service={applicationService} />}>
            <Route element={
              <DashboardLayout
                isSidebarOpen={isSidebarOpen}
                toggleSidebar={() => setIsSidebarOpen(!isSidebarOpen)}
                service={applicationService}
              />
            }>
              {/* <Route path="/metrics" element={<Metrics service={applicationService} />} /> */}
              <Route path="/test-dashboard" element={<TestDashboard service={applicationService} />} />
              <Route path="/employee-profile" element={<EmployeeProfile service={applicationService} />} />
              <Route path="/settings" element={<Settings service={applicationService} />} />
              <Route path="/algos" element={<Algorithms service={applicationService} />} />
            </Route>
          </Route>

        </Routes>
      </div>
    </div>
  );
}
//       <img src={viteLogo} className="vite" alt="Vite logo" />
//     </div>
//     <div>
//       <h1>Get started</h1>
//       <p>
//         Edit <code>src/App.tsx</code> and save to test <code>HMR</code>
//       </p>
//     </div>
//     <button
//       className="counter"
//       onClick={() => setCount((count) => count + 1)}
//     >
//       Count is {count}
//     </button>
//   </section>

//   <div className="ticks"></div>

//   <section id="next-steps">
//     <div id="docs">
//       <svg className="icon" role="presentation" aria-hidden="true">
//         <use href="/icons.svg#documentation-icon"></use>
//       </svg>
//       <h2>Documentation</h2>
//       <p>Your questions, answered</p>
//       <ul>
//         <li>
//           <a href="https://vite.dev/" target="_blank">
//             <img className="logo" src={viteLogo} alt="" />
//             Explore Vite
//           </a>
//         </li>
//         <li>
//           <a href="https://react.dev/" target="_blank">
//             <img className="button-icon" src={reactLogo} alt="" />
//             Learn more
//           </a>
//         </li>
//       </ul>
//     </div>
//     <div id="social">
//       <svg className="icon" role="presentation" aria-hidden="true">
//         <use href="/icons.svg#social-icon"></use>
//       </svg>
//       <h2>Connect with us</h2>
//       <p>Join the Vite community</p>
//       <ul>
//         <li>
//           <a href="https://github.com/vitejs/vite" target="_blank">
//             <svg
//               className="button-icon"
//               role="presentation"
//               aria-hidden="true"
//             >
//               <use href="/icons.svg#github-icon"></use>
//             </svg>
//             GitHub
//           </a>
//         </li>
//         <li>
//           <a href="https://chat.vite.dev/" target="_blank">
//             <svg
//               className="button-icon"
//               role="presentation"
//               aria-hidden="true"
//             >
//               <use href="/icons.svg#discord-icon"></use>
//             </svg>
//             Discord
//           </a>
//         </li>
//         <li>
//           <a href="https://x.com/vite_js" target="_blank">
//             <svg
//               className="button-icon"
//               role="presentation"
//               aria-hidden="true"
//             >
//               <use href="/icons.svg#x-icon"></use>
//             </svg>
//             X.com
//           </a>
//         </li>
//         <li>
//           <a href="https://bsky.app/profile/vite.dev" target="_blank">
//             <svg
//               className="button-icon"
//               role="presentation"
//               aria-hidden="true"
//             >
//               <use href="/icons.svg#bluesky-icon"></use>
//             </svg>
//             Bluesky
//           </a>
//         </li>
//       </ul>
//     </div>
//   </section>

//   <div className="ticks"></div>
//   <section id="spacer"></section>
// </>

export default App
