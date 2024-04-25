mod canvas;
mod graph;

use crate::canvas::Canvas;
use crate::graph::Graph;
use crate::graph::Node;

use glutin_window::GlutinWindow as Window;
use opengl_graphics::{GlGraphics, OpenGL};
use piston::event_loop::{EventSettings, Events};
use piston::input::{RenderArgs, RenderEvent, UpdateArgs, UpdateEvent};
use piston::window::WindowSettings;

use std::fs::File;
use std::io::prelude::*;
use std::io::BufReader;
use std::{env, process};

fn generate_board(file_path: String) -> Vec<Vec<Node>> {
    let file = File::open(file_path).expect("file not found!");
    let buf_reader = BufReader::new(file);
    let mut board: Vec<Vec<Node>> = vec![];
    let mut all_nodes: Vec<Node> = vec![];
    for (x, line) in buf_reader.lines().enumerate() {
        let mut nodes: Vec<Node> = vec![];
        for (y, point) in line.unwrap().split(",").enumerate() {
            let weight = point.parse::<i64>().unwrap();
            let is_wall = if weight == -1 { true } else { false };

            let node = Node {
                cost: weight,
                id: (all_nodes.len()),
                total: <i64>::max_value(),
                dist_to_end: -1,
                is_wall: is_wall,
                x: x as i64,
                best_parent_cord: (0, 0),
                y: y as i64,
                seen: false,
            };
            all_nodes.push(node.clone());
            nodes.push(node);
        }
        board.push(nodes)
    }
    board
}

fn main() -> std::io::Result<()> {
    let args: Vec<String> = env::args().collect();
    let task_number = if Some(&args[1]).is_some() {
        args[1].parse::<i32>().unwrap()
    } else {
        1
    };
    let path: String;
    let start_pos: [i32; 2];
    let end_pos: [i32; 2];
    match task_number {
        1 => {
            path = "./astar/Samfundet_map_1.csv".parse().unwrap();
            start_pos = [27, 18];
            end_pos = [40, 32];
        }
        2 => {
            path = "./astar/Samfundet_map_1.csv".parse().unwrap();
            start_pos = [40, 32];
            end_pos = [8, 5];
        }
        3 => {
            path = "./astar/Samfundet_map_2.csv".parse().unwrap();
            start_pos = [28, 32];
            end_pos = [6, 32];
        }
        4 => {
            path = "./astar/Samfundet_map_Edgar_full.csv".parse().unwrap();
            start_pos = [28, 32];
            end_pos = [6, 32];
        }
        _ => {
            println!("You must enter a task between 1 and 4");
            process::exit(0x0100);
        }
    }
    let board = generate_board(path);
    let mut start = board[start_pos[0] as usize][start_pos[1] as usize].clone();
    let mut end = board[end_pos[0] as usize][end_pos[1] as usize].clone();

    let mut graph = Graph { board: board };
    let ans = graph.astar(&mut start, &mut end);

    let opengl = OpenGL::V3_2;
    let width = 400.0;
    let height = 400.0;

    let mut window: Window = WindowSettings::new("spinning-square", (width, height))
        .graphics_api(opengl)
        .exit_on_esc(true)
        .build()
        .unwrap();

    let mut canvas = Canvas {
        gl: GlGraphics::new(opengl),
        rotation: 0.0,
        path: ans,
        width: width,
        height: height,
        board: graph.board,
        start: start.id,
        end: end.id,
    };

    let mut events = Events::new(EventSettings::new());
    while let Some(e) = events.next(&mut window) {
        if let Some(args) = e.render_args() {
            canvas.render(&args);
        }

        if let Some(args) = e.update_args() {
            canvas.update(&args);
        }
    }

    Ok(())
}
