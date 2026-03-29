import React, { useState } from 'react';
import { User, Bell, Shield, Database, Webhook, Monitor } from 'lucide-react';

const Settings: React.FC<{ service: any }> = ({ service }) => {
  const [activeCategory, setActiveCategory] = useState('Profile');

  const categories = [
    { name: 'Profile', icon: <User size={18} /> },
    { name: 'API & Webhooks', icon: <Webhook size={18} /> },
    { name: 'Environment', icon: <Database size={18} /> },
    { name: 'Notifications', icon: <Bell size={18} /> },
    { name: 'Security', icon: <Shield size={18} /> },
  ];

  return (
    <div className="flex w-full h-full p-8 bg-gray-50">
      {/* Sidebar Navigation */}
      <div className="w-64 space-y-2 pr-8 border-r border-gray-200">
        {categories.map((cat) => (
          <button
            key={cat.name}
            onClick={() => setActiveCategory(cat.name)}
            className={`w-full flex items-center gap-3 px-4 py-3 rounded-xl transition-all ${
              activeCategory === cat.name 
              ? 'bg-[#7964E3] text-white shadow-lg shadow-[#7964E3]/20' 
              : 'text-gray-500 hover:bg-gray-200'
            }`}
          >
            {cat.icon}
            <span className="font-semibold">{cat.name}</span>
          </button>
        ))}
      </div>

      {/* Content Area */}
      {/* Conditional rendering of given settings parts based on whether or not they are active */}
      <div className="flex-1 pl-8">
        <h1 className="text-3xl font-black text-gray-900 mb-8">{activeCategory} Settings</h1>
        
        {activeCategory === 'API & Webhooks' && (
          <div className="space-y-6">
            <div className="p-6 bg-white rounded-2xl border border-gray-200 shadow-sm">
              <h3 className="font-bold text-lg mb-4">Webhook Endpoint</h3>
              <p className="text-sm text-gray-500 mb-4">Configure where AlgoTest should send test completion events.</p>
              <input type="text" className="w-full p-3 bg-gray-50 border border-gray-200 rounded-lg" placeholder="https://api.yourcompany.com/webhooks" />
            </div>
            
            <div className="p-6 bg-white rounded-2xl border border-gray-200 shadow-sm">
              <h3 className="font-bold text-lg mb-4">API Key</h3>
              <div className="flex gap-4">
                <input type="password" value="sk_test_51Mz02..." readOnly className="flex-1 p-3 bg-gray-50 border border-gray-200 rounded-lg" />
                <button className="px-6 py-3 bg-gray-900 text-white rounded-lg font-bold">Rotate Key</button>
              </div>
            </div>
          </div>
        )}

        {activeCategory === 'Environment' && (
          <div className="p-6 bg-white rounded-2xl border border-gray-200 shadow-sm">
            <h3 className="font-bold text-lg mb-4">CARLA Simulation Environment</h3>
            <p className="text-sm text-gray-500 mb-4">Set your default simulation server and telemetry port.</p>
            <div className="grid grid-cols-2 gap-4">
               <input type="text" placeholder="Simulation Host IP" className="p-3 border rounded-lg" />
               <input type="number" placeholder="Telemetry Port (e.g. 8080)" className="p-3 border rounded-lg" />
            </div>
          </div>
        )}
      </div>
    </div>
  );
};

export default Settings;