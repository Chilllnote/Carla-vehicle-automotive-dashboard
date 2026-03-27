import React from 'react';
import './landing.css';

export const Landing: React.FC = () => {
    return (
        <div className="landing">
            {/* Header */}
            <header className="header">
                <div className="container">
                    <h1 className="logo">AlgoTest</h1>
                    <nav className="nav">
                        <a href="#features">Features</a>
                        <a href="#how-it-works">How It Works</a>
                        <a href="#cta" className="btn-primary">Get Started</a>
                    </nav>
                </div>
            </header>

            {/* Hero Section */}
            <section className="hero">
                <div className="container">
                    <h2>Test Automotive Algorithms with Confidence</h2>
                    <p>A powerful UI designed to validate and optimize vehicle control algorithms</p>
                    <button className="btn-primary btn-large">Start Testing</button>
                </div>
            </section>

            {/* Features Section */}
            <section id="features" className="features">
                <div className="container">
                    <h3>Why Choose AlgoTest?</h3>
                    <div className="feature-grid">
                        <div className="feature-card">
                            <h4>Real-Time Visualization</h4>
                            <p>Monitor algorithm performance with live data visualization and metrics</p>
                        </div>
                        <div className="feature-card">
                            <h4>Scenario Simulation</h4>
                            <p>Test algorithms against countless driving scenarios and edge cases</p>
                        </div>
                        <div className="feature-card">
                            <h4>Performance Analytics</h4>
                            <p>Detailed reports on safety, efficiency, and response times</p>
                        </div>
                        <div className="feature-card">
                            <h4>Easy Integration</h4>
                            <p>Seamlessly connect your vehicle control systems</p>
                        </div>
                    </div>
                </div>
            </section>

            {/* How It Works */}
            <section id="how-it-works" className="how-it-works">
                <div className="container">
                    <h3>How It Works</h3>
                    <ol className="steps">
                        <li>Connect your algorithm</li>
                        <li>Configure test scenarios</li>
                        <li>Run simulations</li>
                        <li>Analyze results</li>
                        <li>Optimize and iterate</li>
                    </ol>
                </div>
            </section>

            {/* CTA Section */}
            <section id="cta" className="cta">
                <div className="container">
                    <h3>Ready to Test Your Algorithms?</h3>
                    <button className="btn-primary btn-large">Start Free Trial</button>
                </div>
            </section>

            {/* Footer */}
            <footer className="footer">
                <div className="container">
                    <p>&copy; 2024 AlgoTest. All rights reserved.</p>
                </div>
            </footer>
        </div>
    );
};

export default Landing;