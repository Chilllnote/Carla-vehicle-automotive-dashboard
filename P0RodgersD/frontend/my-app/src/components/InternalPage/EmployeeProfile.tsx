import React, { useState } from 'react';
import { 
  Bell, MessageSquare, ClipboardList, Play, 
  CheckCircle2, XCircle, Plus, Search, Clock, Zap 
} from 'lucide-react';

const EmployeeProfile = () => {
  const [notes, setNotes] = useState("");

  const notifications = [
    { id: 1, type: 'task', text: 'New Task: Validate ODD for Highway Pilot', time: '2m ago', icon: <ClipboardList size={16} /> },
    { id: 2, type: 'chat', text: 'Liam Smith: "The sensor data looks offset..."', time: '15m ago', icon: <MessageSquare size={16} /> },
  ];

  const previousTests = [
    { id: 'TX-992', name: 'Emergency Braking v4', rate: '98.2%', status: 'success' },
    { id: 'TX-988', name: 'Lane Keep Assist - Rain', rate: '64.1%', status: 'fail' },
    { id: 'TX-985', name: 'Adaptive Cruise Control', rate: '100%', status: 'success' },
  ];

  return (
    <div className="flex-1 w-full bg-gray-50 p-6 lg:p-10">
      {/* Header Area */}
      <div className="flex items-center justify-between mb-8">
        <h2 className="text-4xl font-black text-gray-900 tracking-tight">
          Control <span className="text-[#7964E3]">Center</span>
        </h2>
        <div className="flex items-center gap-4">
          <div className="relative cursor-pointer p-2 bg-white rounded-xl border border-gray-200 shadow-sm hover:bg-gray-50 transition-all">
            <Bell size={20} className="text-gray-600" />
            <span className="absolute top-2 right-2 w-2 h-2 bg-red-500 rounded-full border-2 border-white"></span>
          </div>
          <div className="h-10 w-px bg-gray-200"></div>
          <p className="text-sm font-bold text-gray-500 uppercase tracking-tighter">Status: System Nominal</p>
        </div>
      </div>

      <div className="grid grid-cols-1 lg:grid-cols-3 gap-8">
        
        {/* LEFT COLUMN: Notifications & Previous Tasks */}
        <div className="lg:col-span-2 space-y-8">
          
          {/* Notifications Section */}
          <section className="bg-white rounded-3xl border border-gray-200 shadow-sm overflow-hidden">
            <div className="p-6 border-b border-gray-100 flex justify-between items-center">
              <h3 className="font-bold text-gray-800 flex items-center gap-2">
                <Zap size={18} className="text-[#7964E3]" /> Live Updates
              </h3>
              <button className="text-xs font-bold text-[#7964E3] hover:underline uppercase">Clear All</button>
            </div>
            <div className="divide-y divide-gray-50">
              {notifications.map(n => (
                <div key={n.id} className="p-5 flex items-start gap-4 hover:bg-gray-50 transition-colors">
                  <div className={`p-2 rounded-lg ${n.type === 'task' ? 'bg-[#7964E3]/10 text-[#7964E3]' : 'bg-blue-100 text-blue-600'}`}>
                    {n.icon}
                  </div>
                  <div className="flex-1">
                    <p className="text-sm font-semibold text-gray-900">{n.text}</p>
                    <p className="text-xs text-gray-400 mt-1">{n.time}</p>
                  </div>
                </div>
              ))}
            </div>
          </section>

          {/* Previous Tasks & Test History Table */}
          <section className="bg-white rounded-3xl border border-gray-200 shadow-sm overflow-hidden">
            <div className="p-6 border-b border-gray-100">
              <h3 className="font-bold text-gray-800 flex items-center gap-2">
                <Clock size={18} className="text-[#7964E3]" /> Performance History
              </h3>
            </div>
            <div className="overflow-x-auto">
              <table className="w-full">
                <thead>
                  <tr className="bg-gray-50/50 text-left">
                    <th className="px-6 py-4 text-xs font-bold text-gray-400 uppercase tracking-widest">Test ID</th>
                    <th className="px-6 py-4 text-xs font-bold text-gray-400 uppercase tracking-widest">Configuration Name</th>
                    <th className="px-6 py-4 text-xs font-bold text-gray-400 uppercase tracking-widest text-center">Success Rate</th>
                    <th className="px-6 py-4 text-xs font-bold text-gray-400 uppercase tracking-widest text-right">Action</th>
                  </tr>
                </thead>
                <tbody className="divide-y divide-gray-50">
                  {previousTests.map((test) => (
                    <tr key={test.id} className="group hover:bg-gray-50/50 transition-colors">
                      <td className="px-6 py-4 font-mono text-xs text-gray-500">{test.id}</td>
                      <td className="px-6 py-4 font-bold text-gray-800">{test.name}</td>
                      <td className="px-6 py-4 text-center">
                        <span className={`inline-flex items-center gap-1.5 px-3 py-1 rounded-full text-xs font-bold ${
                          test.status === 'success' ? 'bg-green-100 text-green-700' : 'bg-red-100 text-red-700'
                        }`}>
                          {test.status === 'success' ? <CheckCircle2 size={12} /> : <XCircle size={12} />}
                          {test.rate}
                        </span>
                      </td>
                      <td className="px-6 py-4 text-right">
                        <button className="p-2 text-gray-400 hover:text-[#7964E3] hover:bg-[#7964E3]/10 rounded-lg transition-all">
                          <Play size={16} fill="currentColor" />
                        </button>
                      </td>
                    </tr>
                  ))}
                </tbody>
              </table>
            </div>
          </section>
        </div>

        {/* RIGHT COLUMN: Quick Launch & Notes */}
        <div className="space-y-8">
          
          {/* Quick Start Card (High Gloss) */}
          <section className="relative group overflow-hidden bg-gradient-to-br from-[#7964E3] to-[#5a48c3] rounded-3xl p-8 text-white shadow-xl shadow-[#7964E3]/20">
            {/* Glossy Overlay */}
            <div className="absolute top-0 left-0 w-full h-1/2 bg-white/10 pointer-events-none transform -skew-y-6 -translate-y-12 group-hover:-translate-y-8 transition-transform duration-500"></div>
            
            <h3 className="text-xl font-bold mb-2">Resume Validation</h3>
            <p className="text-[#e0dbff] text-sm mb-6 font-medium italic opacity-90">Auto-load: "Rainy_Highway_Full_Sensor_Stack"</p>
            
            <button className="w-full py-4 bg-white text-[#7964E3] font-black rounded-2xl shadow-lg hover:scale-[1.02] active:scale-95 transition-all flex items-center justify-center gap-2">
              <Play size={20} fill="currentColor" />
              Quick Launch Test
            </button>
          </section>

          {/* Notes Section */}
          <section className="bg-white rounded-3xl border border-gray-200 shadow-sm flex flex-col h-[400px]">
            <div className="p-6 border-b border-gray-100 flex items-center justify-between">
              <h3 className="font-bold text-gray-800">Observation Notes</h3>
              <Plus size={20} className="text-gray-400 cursor-pointer hover:text-[#7964E3]" />
            </div>
            <textarea 
              value={notes}
              onChange={(e) => setNotes(e.target.value)}
              placeholder="Jot down sensor anomalies or algorithm behaviors..."
              className="flex-1 p-6 text-sm text-gray-600 focus:outline-none resize-none leading-relaxed placeholder:text-gray-300"
            />
            <div className="p-4 bg-gray-50/50 text-[10px] text-gray-400 uppercase font-bold tracking-widest text-center">
              Auto-saved to Cloud
            </div>
          </section>

        </div>
      </div>
    </div>
  );
};

export default EmployeeProfile;