import React from 'react';

export const TestDashboard: React.FC = () => {
    return (
        <div style={{ padding: '20px', fontFamily: 'Arial, sans-serif' }}>
            <h1>Test Dashboard</h1>
            <div style={{ marginTop: '20px' }}>
                <section style={{ marginBottom: '20px' }}>
                    <h2>Test Status</h2>
                    <p>Ready to run tests</p>
                </section>
                
                <section>
                    <h2>Quick Links</h2>
                    <ul>
                        <li><a href="#tests">View Tests</a></li>
                        <li><a href="#results">Test Results</a></li>
                        <li><a href="#logs">Logs</a></li>
                    </ul>
                </section>
            </div>
        </div>
    );
};

export default TestDashboard;