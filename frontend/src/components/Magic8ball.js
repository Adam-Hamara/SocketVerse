import React, { useState } from "react";
import '.././App.css';
const answers = [
    "Yes, definitely!",
    "No way!",
    "Ask again later.",
    "It is certain.",
    "Very doubtful.",
    "Without a doubt.",
    "Better not tell you now.",
    "Outlook not so good.",
    "Signs point to yes.",
    "Cannot predict now."
];

function Magic8Ball() {
    const [answer, setAnswer] = useState("");
    const [loading, setLoading] = useState(false); // Show "thinking..." effect

    const shakeBall = () => {
        setAnswer(""); // Clear previous answer
        setLoading(true); // Show loading message
        
        setTimeout(() => {
            const randomIndex = Math.floor(Math.random() * answers.length);
            setAnswer(answers[randomIndex]); // Set the new answer
            setLoading(false); // Hide loading message
        }, Math.random() * 1500 + 500);
    };

    return (
        <div className="page">
            <h1>🎱 Magic 8-Ball</h1>
            <button onClick={shakeBall} style={{ padding: "10px 20px", fontSize: "16px" }}>
                Shake the Ball
            </button>
            {loading && <h2 style={{ marginTop: "20px" }}>Shaking well... 🤔</h2>}
            {!loading && answer && <h2 style={{ marginTop: "20px" }}>{answer}</h2>}
        </div>
    );
}

export default Magic8Ball;
