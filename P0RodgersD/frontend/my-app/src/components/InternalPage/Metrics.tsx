import React from 'react';

interface Metric {
    label: string;
    value: string | number;
    icon?: string;
}

const Metrics: React.FC = () => {
    const metrics: Metric[] = [
        { label: 'Total Users', value: '1,234', icon: '👥' },
        { label: 'Revenue', value: '$45,678', icon: '💰' },
        { label: 'Active Sessions', value: '456', icon: '🔄' },
        { label: 'Conversion Rate', value: '3.2%', icon: '📈' },
    ];

    return (
        <div className="min-h-screen bg-gray-50 p-8">
            <h1 className="text-3xl font-bold text-gray-800 mb-8 tracking-tight">
                Metrics Dashboard
            </h1>
            
            <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6">
                {metrics.map((metric, index) => (
                    <div 
                        key={index} 
                        className="relative overflow-hidden group p-6 rounded-2xl bg-white border border-gray-200 shadow-sm transition-all duration-300 hover:shadow-xl hover:-translate-y-1"
                    >
                        {/* Subtle Background Gradient Glow on Hover */}
                        <div className="absolute inset-0 bg-gradient-to-br from-[#7964E3]/5 to-transparent opacity-0 group-hover:opacity-100 transition-opacity" />

                        <div className="relative z-10">
                            <div className="flex items-center justify-between mb-4">
                                {metric.icon && (
                                    <span className="text-2xl p-2 bg-gray-100 rounded-lg group-hover:bg-[#7964E3]/10 transition-colors">
                                        {metric.icon}
                                    </span>
                                )}
                                <span className="text-xs font-bold text-[#7964E3] uppercase tracking-wider opacity-0 group-hover:opacity-100 transition-opacity">
                                    Live Data
                                </span>
                            </div>

                            <p className="text-sm font-medium text-gray-500 mb-1">
                                {metric.label}
                            </p>
                            
                            <h2 className="text-2xl font-bold text-gray-900">
                                {metric.value}
                            </h2>
                        </div>

                        {/* Glossy Accent Bar at the bottom */}
                        <div className="absolute bottom-0 left-0 w-full h-1 bg-gradient-to-r from-[#7964E3] to-[#5a48c3] transform scale-x-0 group-hover:scale-x-100 transition-transform origin-left duration-500" />
                    </div>
                ))}
            </div>
        </div>
    );
};

export default Metrics;