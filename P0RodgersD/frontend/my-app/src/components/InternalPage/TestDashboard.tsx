import React, { useState } from 'react';
import { Play, Square, Cpu, Activity, MapPin, Gauge } from 'lucide-react';

export const TestDashboard: React.FC<{ service: any }> = ({ service }) => {
    const [isRunning, setIsRunning] = useState(false);
    const [selectedAlgo, setSelectedAlgo] = useState("Adaptive Cruise Control");

    return (
        <div className="flex h-full p-6 gap-6 bg-gray-50">
            {/* Left Sidebar: Algorithm Selection */}
            <div className="w-80 bg-white rounded-3xl border border-gray-200 shadow-sm p-6 flex flex-col">
                <h2 className="text-xl font-black mb-6 flex items-center gap-2">
                    <Cpu className="text-[#7964E3]" /> Config
                </h2>
                
                <label className="text-xs font-bold text-gray-400 uppercase tracking-widest mb-2">Select Algorithm</label>
                <select 
                    value={selectedAlgo}
                    onChange={(e) => setSelectedAlgo(e.target.value)}
                    className="w-full p-3 bg-gray-50 border border-gray-200 rounded-xl mb-6 outline-none focus:ring-2 focus:ring-[#7964E3]"
                >
                    {/* Make it so that this is a queried list based on categories */}
                    <option>Adaptive Cruise Control</option>
                    <option>Lane Keeping Assist</option>
                    <option>Emergency Braking</option>
                </select>

                <button 
                    onClick={() => setIsRunning(!isRunning)}
                    className={`w-full py-4 rounded-xl font-bold flex items-center justify-center gap-2 transition-all ${
                        isRunning ? 'bg-red-500 text-white' : 'bg-[#7964E3] text-white'
                    }`}
                >
                    {isRunning ? <Square size={18} /> : <Play size={18} />}
                    {isRunning ? 'Stop Test' : 'Start Simulation'}
                </button>
            </div>

            {/* Main Panel: Live Telemetry */}
            <div className="flex-1 flex flex-col gap-6">
                <div className="bg-white rounded-3xl border border-gray-200 shadow-sm p-8">
                    <h2 className="text-2xl font-black mb-6">Live Telemetry</h2>
                    <div className="grid grid-cols-3 gap-6">
                        {[
                            { label: 'Speed', value: '45.2 km/h', icon: <Gauge /> },
                            { label: 'X-Coord', value: '124.5', icon: <MapPin /> },
                            { label: 'Y-Coord', value: '88.2', icon: <MapPin /> },
                        ].map((stat) => (
                            <div key={stat.label} className="p-5 bg-gray-50 rounded-2xl border border-gray-100">
                                <div className="text-gray-400 mb-2">{stat.icon}</div>
                                <div className="text-xs font-bold text-gray-400 uppercase">{stat.label}</div>
                                <div className="text-2xl font-black text-gray-900">{stat.value}</div>
                            </div>
                        ))}
                    </div>
                </div>

                {/* Status Console */}
                <div className="flex-1 bg-gray-900 rounded-3xl p-6 text-green-400 font-mono text-sm overflow-auto">
                    <p className="opacity-50"># System initialized...</p>
                    {isRunning && <p> Simulation started with {selectedAlgo}...</p>}
                    {isRunning && <p> Telemetry streaming active...</p>}
                </div>
            </div>
        </div>
    );
};

export default TestDashboard;