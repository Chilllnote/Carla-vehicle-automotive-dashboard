import React from 'react';

export const Landing: React.FC<{ service: any }> = ({ service }) => {
    return (
        <div className="bg-white text-gray-900 selection:bg-[#7964E3] selection:text-white">

            {/* Hero Section */}
            <section className="relative pt-32 pb-20 md:pt-48 md:pb-32 bg-[#0a0a0c] overflow-hidden">
                {/* Glossy Background Accent */}
                <div className="absolute top-0 right-0 w-1/2 h-full bg-[#7964E3]/10 blur-[120px] rounded-full -translate-y-1/2 translate-x-1/4" />
                
                <div className="max-w-7xl mx-auto px-6 relative z-10 text-center md:text-left">
                    <div className="inline-block px-4 py-1.5 mb-6 text-sm font-semibold tracking-wide text-[#7964E3] uppercase bg-[#7964E3]/10 rounded-full">
                        Automotive Excellence
                    </div>
                    <h2 className="text-5xl md:text-7xl font-extrabold text-white leading-tight mb-6">
                        Test Your Algorithms <br />
                        <span className="text-[#7964E3]">with Confidence</span>
                    </h2>
                    <p className="text-xl text-gray-400 max-w-2xl mb-10">
                        A high-performance interface built to validate, simulate, and optimize vehicle control systems in real-time.
                    </p>
                    <div className="flex flex-col md:flex-row gap-4">
                        <button 
                        className="px-8 py-4 bg-[#7964E3] text-white font-bold rounded-xl hover:bg-[#6650c9] transition-all transform hover:scale-105 active:scale-95 shadow-xl shadow-[#7964E3]/20"
                        onClick={() => window.location.href = '/test-dashboard'}
                        >
                            Start Testing Now
                        </button>
                        <button 
                        className="px-8 py-4 bg-white/5 text-white font-bold rounded-xl border border-white/10 hover:bg-white/10 transition-all"
                        onClick={() => window.location.href = '/algos'}
                        >
                            View Algorithms
                        </button>
                    </div>
                </div>
            </section>

            {/* Features Section */}
            <section id="features" className="py-24 bg-gray-50">
                <div className="max-w-7xl mx-auto px-6">
                    <div className="text-center mb-16">
                        <h3 className="text-3xl md:text-4xl font-bold mb-4">Why Choose AlgoTest?</h3>
                        <p className="text-gray-600">Engineering-grade tools for modern vehicle development.</p>
                    </div>
                    
                    <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-8">
                        {[
                            { title: 'Real-Time Visualization', desc: 'Monitor performance with sub-millisecond latency telemetry.', icon: '📊' },
                            { title: 'Scenario Simulation', desc: 'Test against thousands of edge cases and traffic patterns.', icon: '🚗' },
                            { title: 'Performance Analytics', desc: 'Detailed reports on safety, efficiency, and system stress.', icon: '🔬' },
                            { title: 'Easy Integration', desc: 'Direct compatibility with CARLA, Python, and C++ stacks.', icon: '🔌' },
                        ].map((feature, i) => (
                            <div key={i} className="p-8 bg-white rounded-2xl border border-gray-200 hover:border-[#7964E3]/50 hover:shadow-xl transition-all group">
                                <div className="text-4xl mb-6">{feature.icon}</div>
                                <h4 className="text-xl font-bold mb-3 group-hover:text-[#7964E3] transition-colors">{feature.title}</h4>
                                <p className="text-gray-500 leading-relaxed">{feature.desc}</p>
                            </div>
                        ))}
                    </div>
                </div>
            </section>

            {/* How It Works */}
            <section id="how-it-works" className="py-24 bg-white border-y border-gray-100">
                <div className="max-w-5xl mx-auto px-6">
                    <h3 className="text-3xl font-bold text-center mb-16">The Testing Lifecycle</h3>
                    <div className="grid grid-cols-1 md:grid-cols-5 gap-4">
                        {['Connect', 'Configure', 'Simulate', 'Analyze', 'Optimize'].map((step, i) => (
                            <div key={i} className="relative text-center">
                                <div className="w-12 h-12 bg-[#7964E3]/10 text-[#7964E3] font-bold rounded-full flex items-center justify-center mx-auto mb-4">
                                    {i + 1}
                                </div>
                                <h5 className="font-bold">{step}</h5>
                                {i < 4 && <div className="hidden md:block absolute top-6 left-[60%] w-full h-[2px] bg-gray-100" />}
                            </div>
                        ))}
                    </div>
                </div>
            </section>

            {/* CTA Section */}
            <section id="cta" className="py-24">
                <div className="max-w-5xl mx-auto px-6">
                    <div className="bg-[#7964E3] rounded-[32px] p-12 md:p-20 text-center relative overflow-hidden shadow-2xl shadow-[#7964E3]/40">
                        {/* Glossy Overlay */}
                        <div className="absolute top-0 left-0 w-full h-full bg-gradient-to-br from-white/20 to-transparent pointer-events-none" />
                        
                        <h3 className="text-4xl md:text-5xl font-black text-white mb-6 relative z-10">
                            Ready to Test Your Algorithms?
                        </h3>
                        <p className="text-[#e0dbff] text-lg mb-10 max-w-xl mx-auto relative z-10">
                            Join the leading automotive labs and start validating your control systems today.
                        </p>
                        <button className="px-10 py-5 bg-white text-[#7964E3] font-black rounded-2xl hover:bg-gray-50 transition-all transform hover:scale-105 shadow-xl relative z-10">
                            Get Started for Free
                        </button>
                    </div>
                </div>
            </section>

            {/* Footer */}
            <footer className="py-12 border-t border-gray-100">
                <div className="max-w-7xl mx-auto px-6 text-center text-gray-500 text-sm">
                    <p>&copy; {new Date().getFullYear()} AlgoTest Systems. Built for high-stakes validation.</p>
                </div>
            </footer>
        </div>
    );
};

export default Landing;