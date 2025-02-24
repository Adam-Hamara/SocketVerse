import React, { useEffect, useState } from "react";
import webSocketService from "../service/WebSocketService";
function Pioupiou() {
    const [nickname, setNickname] = useState("");
    const [isNickname, setIsNickname] = useState(localStorage.getItem("Nickname") !== null);

    useEffect(() => {

        const messageHandler = (message) => {
            const roomsArray = JSON.parse(message);
            
        };

        webSocketService.addListener("pioupiou", messageHandler);

        webSocketService.send('/create_room pioupiou ')

        return () => {
            webSocketService.removeListener("pioupiou");
        };
        
    }, []);

    const saveNickname = (message) => {
        localStorage.removeItem("Nickname")
        localStorage.setItem("Nickname", nickname)
        setIsNickname(true)
    }

    return (
        <div className="page">
            <h1>Pioupiou</h1>
            {
                !isNickname ? (
                    <div>
                        <div className="flex flex-col items-center gap-4 p-4">
                            <input
                                type="text"
                                placeholder="Enter nickname..."
                                value={nickname}
                                onChange={(e) => setNickname(e.target.value)}
                                className="border p-2 rounded-lg"
                            />
                            <button
                                onClick={() => saveNickname(nickname)}
                                className="bg-blue-500 text-white px-4 py-2 rounded-lg hover:bg-blue-600"
                                
                            >
                                Print Text
                            </button>
                        </div>
                    </div>
                ) : (
                    <div>
                        <h1>Game...</h1>
                        <h1>{localStorage.getItem("Nickname")}</h1>
                    </div>
                )
            }

            
        </div>
    );
}

export default Pioupiou;
