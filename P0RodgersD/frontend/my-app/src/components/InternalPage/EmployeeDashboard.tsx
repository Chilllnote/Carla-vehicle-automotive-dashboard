import React, { useMemo, useState } from "react";
import { Search, Users } from "lucide-react"; // Optional: adds to the professional feel

type Employee = {
    id: number;
    name: string;
    title: string;
    department: string;
    email: string;
};

const initialEmployees: Employee[] = [
    { id: 1, name: "Ava Johnson", title: "Software Engineer", department: "Engineering", email: "ava.johnson@example.com" },
    { id: 2, name: "Liam Smith", title: "Product Manager", department: "Product", email: "liam.smith@example.com" },
    { id: 3, name: "Sophia Lee", title: "UX Designer", department: "Design", email: "sophia.lee@example.com" },
    { id: 4, name: "Noah Patel", title: "QA Engineer", department: "Engineering", email: "noah.patel@example.com" },
];

const EmployeeDashboard: React.FC = () => {
    const [employees] = useState<Employee[]>(initialEmployees);
    const [query, setQuery] = useState("");

    const filtered = useMemo(() => {
        const term = query.trim().toLowerCase();
        if (!term) return employees;
        return employees.filter((e) =>
            e.name.toLowerCase().includes(term) ||
            e.title.toLowerCase().includes(term) ||
            e.department.toLowerCase().includes(term) ||
            e.email.toLowerCase().includes(term)
        );
    }, [employees, query]);

    return (
        /* 1. Use flex-1 to grab all space from the DashboardLayout wrapper */
        <div className="flex-1 w-full flex flex-col bg-gray-50">
            
            {/* 2. Remove mx-auto to ensure it hits the edges of the sidebar/screen */}
            <div className="w-full p-6 md:p-10">
                
                {/* Header Section */}
                <div className="flex flex-col lg:flex-row lg:items-center justify-between mb-8 gap-6">
                    <div>
                        <h2 className="text-4xl font-black text-gray-900 tracking-tight">
                            Employee <span className="text-[#7964E3]">Dashboard</span>
                        </h2>
                        <div className="flex items-center gap-2 mt-2 text-gray-500 font-semibold bg-white w-fit px-3 py-1 rounded-full border border-gray-100 shadow-sm">
                            <Users size={16} className="text-[#7964E3]" />
                            <span className="text-sm">Active Records: {employees.length}</span>
                        </div>
                    </div>

                    {/* Search Bar */}
                    <div className="relative w-full lg:w-[450px]">
                        <div className="absolute inset-y-0 left-0 pl-4 flex items-center pointer-events-none text-gray-400">
                            <Search size={20} />
                        </div>
                        <input
                            type="text"
                            value={query}
                            onChange={(e) => setQuery(e.target.value)}
                            placeholder="Search algorithms or personnel..."
                            className="w-full pl-12 pr-4 py-3 bg-white border border-gray-200 rounded-2xl shadow-sm focus:ring-4 focus:ring-[#7964E3]/10 focus:border-[#7964E3] outline-none transition-all placeholder:text-gray-400"
                        />
                    </div>
                </div>

                {/* 3. The Table "Glass" Card - Stretches with w-full */}
                <div className="w-full bg-white rounded-[24px] border border-gray-200 shadow-sm overflow-hidden">
                    <div className="overflow-x-auto">
                        {filtered.length === 0 ? (
                            <div className="p-20 text-center bg-gray-50/30">
                                <p className="text-gray-400 font-medium">No records found for "{query}"</p>
                            </div>
                        ) : (
                            <table className="w-full border-separate border-spacing-0">
                                <thead>
                                    <tr className="bg-gray-50/80">
                                        {/* Added border-b to headers to fix "ghost lines" */}
                                        <th className="text-left px-8 py-5 text-xs font-bold text-gray-400 uppercase tracking-[0.2em] border-b border-gray-100">Name</th>
                                        <th className="text-left px-8 py-5 text-xs font-bold text-gray-400 uppercase tracking-[0.2em] border-b border-gray-100">Title</th>
                                        <th className="text-left px-8 py-5 text-xs font-bold text-gray-400 uppercase tracking-[0.2em] border-b border-gray-100">Department</th>
                                        <th className="text-left px-8 py-5 text-xs font-bold text-gray-400 uppercase tracking-[0.2em] border-b border-gray-100 text-right">Email</th>
                                    </tr>
                                </thead>
                                <tbody className="divide-y divide-gray-50">
                                    {filtered.map((e) => (
                                        <tr key={e.id} className="hover:bg-[#7964E3]/[0.02] transition-colors group">
                                            <td className="px-8 py-5">
                                                <div className="font-bold text-gray-900 group-hover:text-[#7964E3] transition-colors">{e.name}</div>
                                            </td>
                                            <td className="px-8 py-5 text-gray-600 font-medium">{e.title}</td>
                                            <td className="px-8 py-5">
                                                <span className="inline-flex items-center px-3 py-1 rounded-lg text-xs font-bold bg-[#7964E3]/10 text-[#7964E3]">
                                                    {e.department}
                                                </span>
                                            </td>
                                            <td className="px-8 py-5 text-gray-400 font-mono text-xs text-right">{e.email}</td>
                                        </tr>
                                    ))}
                                </tbody>
                            </table>
                        )}
                    </div>
                </div>
            </div>
        </div>
    );
};

export default EmployeeDashboard;