import React, { useEffect, useState } from "react";
import webSocketService from "../service/WebSocketService";
function Games() {
    const [games, setGames] = useState([]);

    useEffect(() => {
        const messageHandler = (message) => {
            const rooms = []
            const roomsArray = JSON.parse(message);
            for(var room of roomsArray){
                rooms.push(room)
            }
            setGames(rooms);
        };

        webSocketService.addListener("games", messageHandler);

        webSocketService.send('/list_games')

        return () => {
            webSocketService.removeListener("games");
        };
        
    }, []);

    return (
        <div className="page">
            <h1>Games</h1>

            { games.length > 0 ? 
                (
                    <ul>
                        { games.map((game, index) => (
                            <a href={`/game/${game.gameId}`}> 
                                <p key={index}>{game.gameName}</p> 
                            </a>
                        ))}
                        
                    </ul>
                ) : 
                (
                    <p>Loading games...</p>
                )
            }
            
        </div>
    );
}

export default Games;
