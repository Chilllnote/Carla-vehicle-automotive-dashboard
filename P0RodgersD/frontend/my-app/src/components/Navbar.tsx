import React from 'react';
import { Link } from 'react-router-dom';

export const Navbar: React.FC = () => {
    return (
        /* 1. Added fixed positioning and z-index to stay on top */
        <nav className="fixed top-0 left-0 w-full h-16 z-50 overflow-hidden bg-gradient-to-b from-[#7964E3]/90 to-[#5a48c3]/90 backdrop-blur-md shadow-lg border-b border-white/20">
            
            {/* The Gloss Highlight Layer */}
            <div className="absolute top-0 left-0 w-full h-1/2 bg-gradient-to-b from-white/20 to-transparent pointer-events-none"></div>

            <div className="relative z-10 max-w-7xl mx-auto px-6 h-full flex items-center justify-between text-white">
                
                {/* Logo - Uses Link for home navigation */}
                <Link to="/" className="text-xl font-black tracking-tighter drop-shadow-md hover:opacity-90 transition-opacity">
                    Don's <span className="text-white/80">Algorithm Tests</span>
                </Link>

                {/* Navigation Links - Swapped <a> for <Link> */}
                <ul className="flex items-center space-x-8 text-sm font-semibold uppercase tracking-wide">
                    <li>
                        <Link to="/" className="hover:text-white/70 transition-colors drop-shadow-sm">
                            Home
                        </Link>
                    </li>
                    <li>
                        <Link to="/test-dashboard" className="hover:text-white/70 transition-colors drop-shadow-sm">
                            Simulator
                        </Link>
                    </li>
                    {/* <li>
                        <Link to="/metrics" className="hover:text-white/70 transition-colors drop-shadow-sm">
                            Metrics
                        </Link>
                    </li> */}
                    
                    {/* Profile Action */}
                    <li>
                        <Link 
                            to="/employee-profile" 
                            className="bg-white/10 border border-white/20 px-5 py-2 rounded-full hover:bg-white/20 hover:shadow-[0_0_20px_rgba(255,255,255,0.2)] transition-all active:scale-95"
                        >
                            DonovR
                        </Link>
                    </li>
                </ul>
            </div>
        </nav>
    );
};