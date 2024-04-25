extern crate glutin_window;
extern crate graphics;
extern crate opengl_graphics;
extern crate piston;

use opengl_graphics::GlGraphics;
use piston::input::{RenderArgs, RenderEvent, UpdateArgs, UpdateEvent};

use crate::graph::Node;

pub struct Canvas {
    pub gl: GlGraphics, // OpenGL drawing backend.
    pub rotation: f64,  // Rotation for the square.
    pub board: Vec<Vec<Node>>,
    pub path: Vec<i64>,
    pub width: f64,
    pub height: f64,
    pub start: usize,
    pub end: usize,
}
const BLACK: [f32; 4] = [0.0, 0.0, 0.0, 1.0];
const GREEN: [f32; 4] = [0.0, 1.0, 0.0, 1.0];
const YELLOW: [f32; 4] = [1.0, 1.0, 0.0, 1.0];
const BLUE: [f32; 4] = [0.0, 0.0, 1.0, 1.0];
const RED: [f32; 4] = [1.0, 0.0, 0.0, 1.0];
const LIGHTGRAY: [f32; 4] = [0.823, 0.823, 0.823, 1.0];
const GRAY: [f32; 4] = [0.5, 0.5, 0.5, 1.0];
const DARKGRAY: [f32; 4] = [0.18, 0.31, 0.31, 1.0];

impl Canvas {
    pub fn render(&mut self, args: &RenderArgs) {
        use graphics::*;
        let box_size: f64 = self.width / self.board.len() as f64;

        self.gl.draw(args.viewport(), |c, gl| {
            clear(RED, gl);

            let mut xp: f64 = 0.0;
            let mut yp: f64 = 0.0;
            for nodes in self.board.iter() {
                for node in nodes.iter() {
                    let x = xp * box_size;
                    let y = yp * box_size;
                    let square = rectangle::square(x, y, box_size);
                    let id = node.id as i64;
                    let mut color: [f32; 4] = RED;
                    match node.cost {
                        -1 => color = RED,
                        1 => color = LIGHTGRAY,
                        2 => color = GRAY,
                        3 => color = DARKGRAY,
                        4 => color = BLACK,
                        _ => (),
                    }
                    if self.path.contains(&id) {
                        color = YELLOW
                    }
                    if node.id == self.start {
                        color = GREEN
                    }
                    if node.id == self.end {
                        color = BLUE
                    }
                    rectangle(color, square, c.transform, gl);
                    xp += 1.0;
                }
                yp += 1.0;
                xp = 0.0;
            }
        });
    }

    pub fn update(&mut self, args: &UpdateArgs) {
        // Rotate 2 radians per second.
    }
}
