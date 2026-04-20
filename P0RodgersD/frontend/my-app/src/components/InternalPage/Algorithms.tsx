import React, { useState, useMemo } from 'react';
import { Brain, Car, ShieldCheck, Zap, Activity, ChevronRight, BarChart3 } from 'lucide-react';
import type { AIAlgo, ms } from '../../dtos/type';
import Card from './Card';


const Algorithms: React.FC<{ service: any }> = ({ service }) => {
  const [selectedCategory, setSelectedCategory] = useState('All');

  const categories = ['All', 'Computer Vision', 'Motion Planning', 'Safety Systems', 'Sensor Fusion'];

  return (
    <div className="flex h-full bg-gray-50 overflow-hidden">
      {/* 1. Category Sidebar */}
      <div className="w-64 bg-white border-r border-gray-200 p-6">
        <h2 className="text-xs font-bold text-gray-400 uppercase tracking-widest mb-6">AI Categories</h2>
        <div className="space-y-2">
          {categories.map(cat => (
            <button
              key={cat}
              onClick={() => { setSelectedCategory(cat); }}
              className={`w-full text-left px-4 py-2 rounded-lg font-medium transition-all ${selectedCategory === cat ? 'bg-[#7964E3]/10 text-[#7964E3]' : 'text-gray-600 hover:bg-gray-100'
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
          <Card />
          <div className="grid gap-4">

          </div>
        </div>
      </div>
    </div>
  );
};

export default Algorithms;