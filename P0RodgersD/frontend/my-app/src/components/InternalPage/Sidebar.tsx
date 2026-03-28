import React, { useState } from 'react';
import { Home, BarChart2, Cpu, Settings, X, ChevronLeft, ChevronRight, LogOut } from 'lucide-react';

interface SidebarProps {
    isOpen?: boolean;
    onToggle?: () => void;
}

const Sidebar: React.FC<SidebarProps> = ({ isOpen = true, onToggle }) => {
    const [activeTab, setActiveTab] = useState('Home');

    const menuItems = [
        { name: 'Home', icon: <Home size={20} />, path: '/' },
        { name: 'Algorithms', icon: <Cpu size={20} />, path: '/algos' },
        { name: 'Metrics', icon: <BarChart2 size={20} />, path: '/metrics' },
        { name: 'Settings', icon: <Settings size={20} />, path: '/settings' },
    ];

    return (
        <aside 
            className={`fixed left-0 top-0 h-screen transition-all duration-300 ease-in-out z-40 pt-16
                ${isOpen ? 'w-64' : 'w-20'} 
                bg-[#0a0a0c] border-r border-white/5 flex flex-col`}
        >

            {/* Logo Section */}
            <div className="p-6 flex items-center h-20 border-b border-white/5 relative">
                {/* Text stays in flow, but we hide it when closed */}
                <div className={`font-black text-xl tracking-tighter text-[#7964E3] transition-all duration-300 ${
                    isOpen ? 'opacity-100 translate-x-0' : 'opacity-0 -translate-x-10 pointer-events-none'
                }`}>
                    ALGO<span className="text-white">TEST</span>
                </div>
                
                {/* Toggle Button: Positioned absolutely so it doesn't fight the Logo for space */}
                <button 
                    onClick={onToggle}
                    className={`absolute right-4 top-1/2 -translate-y-1/2 p-1.5 rounded-lg bg-white/5 text-gray-400 hover:text-white hover:bg-[#7964E3]/20 transition-all z-50`}
                >
                    {isOpen ? <ChevronLeft size={18} /> : <ChevronRight size={18} />}
                </button>

                <div className="absolute top-0 left-0 w-full h-px bg-gradient-to-r from-transparent via-white/20 to-transparent"></div>
            </div>

            {/* Navigation Links */}
            <nav className="flex-1 px-3 py-6 space-y-2">
                {menuItems.map((item) => (
                    <a
                        key={item.name}
                        href={item.path}
                        onClick={(e) => {
                            e.preventDefault();
                            setActiveTab(item.name);
                        }}
                        className={`group flex items-center p-3 rounded-xl transition-all relative overflow-hidden
                            ${activeTab === item.name 
                                ? 'text-white bg-[#7964E3]/10 shadow-[inset_0_0_15px_rgba(121,100,227,0.1)]' 
                                : 'text-gray-400 hover:text-white hover:bg-white/5'
                            }`}
                    >
                        {/* Active Indicator Bar */}
                        {activeTab === item.name && (
                            <div className="absolute left-0 top-2 bottom-2 w-1 bg-[#7964E3] rounded-r-full" />
                        )}

                        <div className={`transition-colors ${activeTab === item.name ? 'text-[#7964E3]' : 'group-hover:text-white'}`}>
                            {item.icon}
                        </div>

                        <span className={`ml-4 font-medium transition-all duration-200 whitespace-nowrap ${!isOpen && 'opacity-0 translate-x-10'}`}>
                            {item.name}
                        </span>

                        {/* Hover Tooltip for Collapsed State */}
                        {!isOpen && (
                            <div className="absolute left-16 scale-0 group-hover:scale-100 transition-transform bg-[#1a1a1c] text-white text-xs px-2 py-1 rounded border border-white/10 pointer-events-none">
                                {item.name}
                            </div>
                        )}
                    </a>
                ))}
            </nav>

            {/* User Profile / Footer Section */}
            <div className="p-4 border-t border-white/5 bg-white/[0.02]">
                <div className={`flex items-center transition-all ${!isOpen ? 'justify-center' : 'gap-3'}`}>
                    <div className="w-10 h-10 rounded-full bg-gradient-to-br from-[#7964E3] to-[#5a48c3] flex items-center justify-center text-white font-bold border border-white/20">
                        DR
                    </div>
                    {isOpen && (
                        <div className="flex-1 overflow-hidden">
                            <p className="text-sm font-bold text-white truncate">Donov Rodgers</p>
                            <p className="text-xs text-gray-500 truncate">Software Engineer</p>
                        </div>
                    )}
                </div>
                
                {isOpen && (
                    <button className="mt-4 w-full flex items-center justify-center gap-2 py-2 px-4 rounded-lg text-gray-400 hover:text-red-400 hover:bg-red-400/10 transition-colors text-sm font-medium">
                        <LogOut size={16} />
                        <span>Logout</span>
                    </button>
                )}
            </div>
        </aside>
    );
};

export default Sidebar;