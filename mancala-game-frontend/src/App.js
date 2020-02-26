import React, { Component } from 'react';
import './App.css';

function Pit(props) {
  if (props.disabled) {
    return (
      <button className="pit" disabled>
        {props.value}
      </button>
    );
  } else {
    return (
      <button onClick={props.onClick} className="pit">
        {props.value}
      </button>
    );
  }
}

function BigPit(props) {
  return (
    <button className="pit" disabled>
      {props.value}
    </button>
  );
}

class Game extends Component {

  state = {
    playersPits: [],
    gameHasEnded: false,
    errorMessage: ''
  }

  newGame() {
    fetch('http://localhost:8080/mancala',
      {
        method: 'POST'
      })
      .then(res => res.json())
      .then((data) => {
        this.setState({
          id: data.id,
          playerTurn: data.nextPlayerTurn,
          playersPits: data.playersPits,
          gameHasEnded: false,
          errorMessage: ''
        })
      })
      .catch(console.log)
  }

  move(position) {
    let jsonBody = JSON.stringify({ player: this.state.playerTurn, position: position });
    fetch('http://localhost:8080/mancala/' + this.state.id + '/move',
      {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json'
        },
        body: jsonBody
      })
      .then(res => res.json())
      .then((data) => {
        if (data.status === 500) {
          this.setState({
            errorMessage: data.message
          })
        } else {
          this.setState({
            id: data.id,
            playerTurn: data.nextPlayerTurn,
            playersPits: data.playersPits,
            gameHasEnded: data.gameHasEnded,
            winner: data.winner,
            errorMessage: ''
          })
        }
      })
      .catch(console.log)
  }

  renderPit(pits, player, position) {
    var stonesNumber = pits === undefined ? 0 : pits[position];
    return <Pit
      onClick={() => this.move(position)}
      value={stonesNumber}
      disabled={player !== this.state.playerTurn || this.state.gameHasEnded}
    />;
  }

  renderBigPit(pits, position) {
    var stonesNumber = pits === undefined ? 0 : pits[position];
    return <BigPit
      value={stonesNumber}
    />;
  }

  render() {
    let status = '';
    if (this.state.gameHasEnded) {
      status = 'Winner: ' + this.state.winner;
    } else if (this.state.playerTurn !== undefined) {
      status = 'Next player: ' + this.state.playerTurn;
    }

    return (
      <div>
        <button onClick={() => this.newGame()}>New game</button>
        <div>{status}</div>
        <div className='error'>{this.state.errorMessage}</div>
        <div>
          <div>
            <table>
              <tbody>
                <tr>
                  <th />
                  <th>
                    {this.renderPit(this.state.playersPits.PLAYER1, 'PLAYER1', 5)}
                  </th>
                  <th>
                    {this.renderPit(this.state.playersPits.PLAYER1, 'PLAYER1', 4)}
                  </th>
                  <th>
                    {this.renderPit(this.state.playersPits.PLAYER1, 'PLAYER1', 3)}
                  </th>
                  <th>
                    {this.renderPit(this.state.playersPits.PLAYER1, 'PLAYER1', 2)}
                  </th>
                  <th>
                    {this.renderPit(this.state.playersPits.PLAYER1, 'PLAYER1', 1)}
                  </th>
                  <th>
                    {this.renderPit(this.state.playersPits.PLAYER1, 'PLAYER1', 0)}
                  </th>
                </tr>
                <tr>
                  <th>
                    {this.renderBigPit(this.state.playersPits.PLAYER1, 6)}
                  </th>
                  <th />
                  <th />
                  <th />
                  <th />
                  <th />
                  <th />
                  <th>
                    {this.renderBigPit(this.state.playersPits.PLAYER2, 6)}
                  </th>
                </tr>
                <tr>
                  <th />
                  <th>
                    {this.renderPit(this.state.playersPits.PLAYER2, 'PLAYER2', 0)}
                  </th>
                  <th>
                    {this.renderPit(this.state.playersPits.PLAYER2, 'PLAYER2', 1)}
                  </th>
                  <th>
                    {this.renderPit(this.state.playersPits.PLAYER2, 'PLAYER2', 2)}
                  </th>
                  <th>
                    {this.renderPit(this.state.playersPits.PLAYER2, 'PLAYER2', 3)}
                  </th>
                  <th>
                    {this.renderPit(this.state.playersPits.PLAYER2, 'PLAYER2', 4)}
                  </th>
                  <th>
                    {this.renderPit(this.state.playersPits.PLAYER2, 'PLAYER2', 5)}
                  </th>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    );
  }
}

export default Game;
