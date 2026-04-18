import { ChevronRight, Zap } from 'lucide-react'
import React, { useMemo, useState } from 'react'
import type { AIAlgo } from '../../dtos/type';


const Card: React.FC = () => {

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

    const [activeAlgo, setActiveAlgo] = useState<AIAlgo | null>(null);


    return (

        <div className="grid gap-6 mb-8 md:grid-cols-2 lg:grid-cols-3">
            {ALGO_LIBRARY.map(algo => (
                <div
                    key={algo.id}
                    onClick={() => setActiveAlgo(algo)}
                    className={`p-6 bg-white rounded-2xl border transition-all cursor-pointer group ${activeAlgo?.id === algo.id ? 'border-[#7964E3] shadow-md' : 'border-gray-200 hover:border-[#7964E3]/50'
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
                            <div className='bg-blue-500'>
                                <p className="text-gray-600 mb-6 leading-relaxed">{algo.description}</p>
                            </div>

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

    )
}

export default Card;