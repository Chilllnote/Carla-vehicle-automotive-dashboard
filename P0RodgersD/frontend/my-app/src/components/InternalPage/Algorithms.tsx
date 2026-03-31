import React, { useState, useMemo } from 'react';
import { Brain, Car, ShieldCheck, Zap, Activity, ChevronRight, BarChart3 } from 'lucide-react';
import type { AIAlgo, ms } from '../../dtos/type';

const ALGO_LIBRARY: AIAlgo[] = [
  { 
    id: 'acc-01', 
    name: 'Adaptive Cruise Control v4', 
    category: 'Motion Planning', 
    description: 'Uses reinforcement learning to maintain safe distance in CARLA urban environments.',
    stats: { passRate: 94, failRate: 6, avgLatency: 12 } 
  },
  { 
    id: 'lka-02', 
    name: 'Lane Keep Assist (CNN)', 
    category: 'Computer Vision', 
    description: 'Convolutional Neural Network trained on highway lane markings.',
    stats: { passRate: 88, failRate: 12, avgLatency: 24 } 
  },
  { 
    id: 'eb-03', 
    name: 'Emergency Braking Pro', 
    category: 'Safety Systems', 
    description: 'Heuristic-based emergency stop triggered by ultrasonic sensor telemetry.',
    stats: { passRate: 99, failRate: 1, avgLatency: 5 } 
  }
];

const Algorithms: React.FC<{ service: any }> = ({ service }) => {
  const [selectedCategory, setSelectedCategory] = useState('All');
  const [activeAlgo, setActiveAlgo] = useState<AIAlgo | null>(null);

  const categories = ['All', 'Computer Vision', 'Motion Planning', 'Safety Systems', 'Sensor Fusion'];

  const filteredAlgos = useMemo(() => 
    selectedCategory === 'All' ? ALGO_LIBRARY : ALGO_LIBRARY.filter(a => a.category === selectedCategory)
  , [selectedCategory]);

  return (
    <div className="flex h-full bg-gray-50 overflow-hidden">
      {/* 1. Category Sidebar */}
      <div className="w-64 bg-white border-r border-gray-200 p-6">
        <h2 className="text-xs font-bold text-gray-400 uppercase tracking-widest mb-6">AI Categories</h2>
        <div className="space-y-2">
          {categories.map(cat => (
            <button
              key={cat}
              onClick={() => { setSelectedCategory(cat); setActiveAlgo(null); }}
              className={`w-full text-left px-4 py-2 rounded-lg font-medium transition-all ${
                selectedCategory === cat ? 'bg-[#7964E3]/10 text-[#7964E3]' : 'text-gray-600 hover:bg-gray-100'
              }`}
            >
              {cat}
            </button>
          ))}
        </div>
      </div>

      {/* 2. Algorithm List */}
      <div className="flex-1 p-8 overflow-y-auto">
        <div className="max-w-4xl mx-auto">
          <h1 className="text-3xl font-black text-gray-900 mb-8">Algorithm <span className="text-[#7964E3]">Library</span></h1>
          
          <div className="grid gap-4">
            {filteredAlgos.map(algo => (
              <div 
                key={algo.id}
                onClick={() => setActiveAlgo(algo)}
                className={`p-6 bg-white rounded-2xl border transition-all cursor-pointer group ${
                  activeAlgo?.id === algo.id ? 'border-[#7964E3] shadow-md' : 'border-gray-200 hover:border-[#7964E3]/50'
                }`}
              >
                <div className="flex justify-between items-center">
                  <div>
                    <span className="text-[10px] font-bold text-[#7964E3] bg-[#7964E3]/10 px-2 py-1 rounded uppercase mb-2 inline-block">
                      {algo.category}
                    </span>
                    <h3 className="text-xl font-bold text-gray-900">{algo.name}</h3>
                  </div>
                  <ChevronRight className={`transition-transform ${activeAlgo?.id === algo.id ? 'rotate-90 text-[#7964E3]' : 'text-gray-300'}`} />
                </div>

                {/* 3. Detailed Information (Expanded State) */}
                {activeAlgo?.id === algo.id && (
                  <div className="mt-6 pt-6 border-t border-gray-100 animate-in fade-in slide-in-from-top-2">
                    <p className="text-gray-600 mb-6 leading-relaxed">{algo.description}</p>
                    
                    <div className="grid grid-cols-3 gap-4">
                      <div className="p-4 bg-green-50 rounded-xl">
                        <div className="text-xs font-bold text-green-600 uppercase">Pass Rate</div>
                        <div className="text-2xl font-black text-green-700">{algo.stats.passRate}%</div>
                      </div>
                      <div className="p-4 bg-red-50 rounded-xl">
                        <div className="text-xs font-bold text-red-600 uppercase">Failure Rate</div>
                        <div className="text-2xl font-black text-red-700">{algo.stats.failRate}%</div>
                      </div>
                      <div className="p-4 bg-blue-50 rounded-xl">
                        <div className="text-xs font-bold text-blue-600 uppercase">Avg Latency</div>
                        <div className="text-2xl font-black text-blue-700">{algo.stats.avgLatency}ms</div>
                      </div>
                    </div>

                    <button className="mt-6 w-full py-3 bg-gray-900 text-white rounded-xl font-bold hover:bg-[#7964E3] transition-colors flex items-center justify-center gap-2">
                      <Zap size={18} /> Configure for Test Run
                    </button>
                  </div>
                )}
              </div>
            ))}
          </div>
        </div>
      </div>
    </div>
  );
};

export default Algorithms;