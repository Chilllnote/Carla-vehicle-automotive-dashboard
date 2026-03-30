import React, { useEffect, useState } from 'react';
import { 
  Bell, Play, CheckCircle2, XCircle, Plus, Clock, 
  Fingerprint, Briefcase, Mail, X 
} from 'lucide-react';

import EmployeeMetadataService from '../../utils/EmployeeMetaData';
import type { EmployeeMetadataDTO } from '../../dtos/type';

const EmployeeProfile = ({ service }: { service: any }) => {
  const [notes, setNotes] = useState("");
  const [employeeData, setEmployeeData] = useState<EmployeeMetadataDTO | null>(null);
  const [loading, setLoading] = useState(true);

  // --- New State for Modal ---
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [noteForm, setNoteForm] = useState({ title: '', description: '' });

  const previousTests = [
    { id: 'TX-992', name: 'Emergency Braking v4', rate: '98.2%', status: 'success' },
    { id: 'TX-988', name: 'Lane Keep Assist - Rain', rate: '64.1%', status: 'fail' },
    { id: 'TX-985', name: 'Adaptive Cruise Control', rate: '100%', status: 'success' },
  ];

  useEffect(() => {
    const fetchData = async () => {
      try {
        setLoading(true);
        let username = "donovr"; // Hardcoded for testing
        if (username) {
          const result = await EmployeeMetadataService.getByUsername(username);
          setEmployeeData(result);
        }
      } catch (err) {
        console.error("Error loading dashboard data:", err);
      } finally {
        setLoading(false);
      }
    };
    fetchData();
  }, [service.currentUser?.username]);

  // --- Modal Handlers ---
  const handleOpenModal = () => setIsModalOpen(true);
  const handleCloseModal = () => {
    setIsModalOpen(false);
    setNoteForm({ title: '', description: '' }); // Reset form
  };

  const handleFormSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    // This is where you'd typically call an API service
    console.log("Submitting Note Data:", {
      userId: employeeData?.id,
      ...noteForm,
      timestamp: new Date().toISOString()
    });
    
    // Update local preview notes if desired
    setNotes(prev => `${prev}\n\n[${noteForm.title.toUpperCase()}]\n${noteForm.description}`);
    handleCloseModal();
  };

  const handleQuickLaunch = async () => {
    try {
      if (employeeData?.username) {
        const data = await EmployeeMetadataService.getByUsername(employeeData.username); 
        console.log("Quick Launch Metadata Refresh:", data);
      }
    } catch (error) {
      console.error("Failed to fetch metadata:", error);
    }
  };  

  if (loading) return (
    <div className="flex-1 w-full h-screen bg-gray-50 flex items-center justify-center">
      <div className="flex flex-col items-center gap-4">
        <div className="w-12 h-12 border-4 border-[#7964E3] border-t-transparent rounded-full animate-spin"></div>
        <p className="text-gray-500 font-bold animate-pulse">Initializing Control Center...</p>
      </div>
    </div>
  );

  if (!employeeData) return <div className="p-10 text-center">No employee data found.</div>;

  return (
    <div className="relative flex-1 w-full bg-gray-50 p-6 lg:p-10">
      
      {/* --- FORM MODAL OVERLAY --- */}
      {isModalOpen && (
        <div className="fixed inset-0 z-50 flex items-center justify-center p-4 bg-black/50 backdrop-blur-sm">
          <div className="bg-white w-full max-w-md rounded-3xl shadow-2xl overflow-hidden animate-in fade-in zoom-in duration-200">
            <div className="p-6 border-b border-gray-100 flex justify-between items-center">
              <h3 className="text-xl font-black text-gray-900">Create Official <span className="text-[#7964E3]">Log</span></h3>
              <button onClick={handleCloseModal} className="p-2 hover:bg-gray-100 rounded-full transition-colors">
                <X size={20} className="text-gray-400" />
              </button>
            </div>
            <form onSubmit={handleFormSubmit} className="p-6 space-y-4">
              <div>
                <label className="block text-xs font-black text-gray-400 uppercase tracking-widest mb-2">Note Title</label>
                <input 
                  required
                  type="text" 
                  className="w-full p-4 bg-gray-50 border border-gray-200 rounded-2xl focus:outline-none focus:ring-2 focus:ring-[#7964E3]/20 focus:border-[#7964E3] transition-all"
                  placeholder="e.g. Sensor Latency Spike"
                  value={noteForm.title}
                  onChange={(e) => setNoteForm({...noteForm, title: e.target.value})}
                />
              </div>
              <div>
                <label className="block text-xs font-black text-gray-400 uppercase tracking-widest mb-2">Detailed Description</label>
                <textarea 
                  required
                  rows={4}
                  className="w-full p-4 bg-gray-50 border border-gray-200 rounded-2xl focus:outline-none focus:ring-2 focus:ring-[#7964E3]/20 focus:border-[#7964E3] transition-all resize-none"
                  placeholder="Describe the anomaly or observation..."
                  value={noteForm.description}
                  onChange={(e) => setNoteForm({...noteForm, description: e.target.value})}
                />
              </div>
              <button 
                type="submit"
                className="w-full py-4 bg-[#7964E3] text-white font-black rounded-2xl shadow-lg shadow-[#7964E3]/30 hover:bg-[#5a48c3] transition-all"
              >
                Save Observation
              </button>
            </form>
          </div>
        </div>
      )}

      {/* Header Area */}
      <div className="flex items-center justify-between mb-8">
        <div className="flex flex-col">
          <h2 className="text-4xl font-black text-gray-900 tracking-tight">
            Control <span className="text-[#7964E3]">Center</span>
          </h2>
          <p className="text-gray-400 text-xs font-bold uppercase mt-1">Terminal Session: Active</p>
        </div>
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
        <div className="lg:col-span-2 space-y-8">
          {/* Entity Header Section */}
          <section className="bg-white rounded-3xl border border-gray-200 shadow-sm p-8 flex flex-col md:flex-row items-center md:items-start gap-6">
            <div className="w-24 h-24 rounded-2xl bg-gradient-to-br from-[#7964E3] to-[#5a48c3] flex items-center justify-center text-white text-3xl font-black shadow-lg shadow-[#7964E3]/30 shrink-0">
              {employeeData.name ? employeeData.name.split(' ').map(n => n[0]).join('') : '??'}
            </div>
            
            <div className="flex-1 text-center md:text-left">
              <div className="flex flex-col md:flex-row items-center gap-3 mb-1">
                <h2 className="text-3xl font-black text-gray-900">{employeeData.name}</h2>
                <span className="px-3 py-1 bg-[#7964E3]/10 text-[#7964E3] text-[10px] font-bold uppercase rounded-full tracking-wider border border-[#7964E3]/20">
                  {employeeData.role}
                </span>
              </div>
              <div className="grid grid-cols-1 md:grid-cols-2 gap-y-3 mt-4">
                <div className="flex items-center justify-center md:justify-start gap-2 text-gray-500">
                  <Mail size={16} className="text-[#7964E3]" />
                  <span className="text-sm font-medium">{employeeData.email}</span>
                </div>
                <div className="flex items-center justify-center md:justify-start gap-2 text-gray-500">
                  <Briefcase size={16} className="text-[#7964E3]" />
                  <span className="text-sm font-medium">{employeeData.department}</span>
                </div>
                <div className="flex items-center justify-center md:justify-start gap-2 text-gray-500">
                  <Fingerprint size={16} className="text-[#7964E3]" />
                  <span className="text-xs font-mono uppercase tracking-tighter">UID: {employeeData.id}</span>
                </div>
              </div>
            </div>
          </section>

          {/* Performance History Table */}
          <section className="bg-white rounded-3xl border border-gray-200 shadow-sm overflow-hidden">
            <div className="p-6 border-b border-gray-100 flex items-center justify-between">
              <h3 className="font-bold text-gray-800 flex items-center gap-2">
                <Clock size={18} className="text-[#7964E3]" /> Performance History
              </h3>
              <div className="text-[10px] font-black text-gray-400 uppercase tracking-widest">Global Ranking: Top 5%</div>
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
          <section className="relative group overflow-hidden bg-gradient-to-br from-[#7964E3] to-[#5a48c3] rounded-3xl p-8 text-white shadow-xl shadow-[#7964E3]/20">
            <div className="absolute top-0 left-0 w-full h-1/2 bg-white/10 pointer-events-none transform -skew-y-6 -translate-y-12 group-hover:-translate-y-8 transition-transform duration-500"></div>
            <h3 className="text-xl font-bold mb-2">Resume Validation</h3>
            <p className="text-[#e0dbff] text-sm mb-6 font-medium italic opacity-90">Auto-load: "Rainy_Highway_Full_Sensor_Stack"</p>
            <button 
              className="w-full py-4 bg-white text-[#7964E3] font-black rounded-2xl shadow-lg hover:scale-[1.02] active:scale-95 transition-all flex items-center justify-center gap-2"
              onClick={handleQuickLaunch}
            >
              <Play size={20} fill="currentColor" />
              Quick Launch Test
            </button>
          </section>

          {/* Notes Section */}
          <section className="bg-white rounded-3xl border border-gray-200 shadow-sm flex flex-col h-[400px]">
            <div className="p-6 border-b border-gray-100 flex items-center justify-between">
              <h3 className="font-bold text-gray-800">Observation Notes</h3>
              {/* --- PLUS ICON NOW TRIGGERS MODAL --- */}
              <Plus 
                size={20} 
                className="text-gray-400 cursor-pointer hover:text-[#7964E3] hover:scale-110 transition-all" 
                onClick={handleOpenModal}
              />
            </div>
            <textarea 
              value={notes}
              onChange={(e) => setNotes(e.target.value)}
              placeholder="Jot down sensor anomalies or algorithm behaviors..."
              className="flex-1 p-6 text-sm text-gray-600 focus:outline-none resize-none leading-relaxed placeholder:text-gray-300"
            />
            <div className="p-4 bg-gray-50/50 text-[10px] text-gray-400 uppercase font-bold tracking-widest text-center">
              Logged as: {employeeData.username}
            </div>
          </section>
        </div>
      </div>
    </div>
  );
};

export default EmployeeProfile;